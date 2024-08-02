package com.example.block.service;

import com.example.block.domain.User;
import com.example.block.dto.JwtTokenDto;
import com.example.block.dto.SignInRequest;
import com.example.block.dto.SignUpRequest;
import com.example.block.dto.UserLoginDto;
import com.example.block.global.apiPayload.code.status.ErrorStatus;
import com.example.block.global.apiPayload.exception.GeneralException;
import com.example.block.global.constants.Constants;
import com.example.block.repository.UserRepository;
import com.example.block.security.enums.ERole;
import com.example.block.utillity.JwtUtil;
import com.example.block.utillity.PasswordEncoder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public JwtTokenDto login(UserLoginDto userLoginDto) {
        User user;
        boolean isNewUser = false;

        Optional<User> existingUser = userRepository.findBySerialId(userLoginDto.providerId());

        if (existingUser.isPresent()) {
            user = existingUser.get();
        } else {
            user = userRepository.save(User.signUp(userLoginDto.providerId()));
            isNewUser = true;
            user.setIsNewUser(false);
            user.setPlatform(Constants.PLATFORM_GENERAL);
        }

        JwtTokenDto jwtTokenDto = jwtUtil.generateTokens(user.getId(), ERole.USER);

        if (isNewUser || !jwtTokenDto.refreshToken().equals(user.getRefreshToken())) {
            userRepository.updateRefreshTokenAndLoginStatus(user.getId(), jwtTokenDto.refreshToken(), true);
        }

        return jwtTokenDto;
    }

    @Transactional
    public JwtTokenDto signUp(SignUpRequest request) {

        //전역변수로 두기 위해
        User user;

        // 해당 이메일로 가입된 적이 있는지 여부 확인
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if(existingUser.isPresent()){
            throw new GeneralException(ErrorStatus.EXIST_EMAIL);
        }

        Optional<User> where_user = userRepository.findBySerialId(request.getProviderId());

        if (where_user.isEmpty()) {
            if (request.getPlatform().equals(Constants.PLATFORM_KAKAO)) {
                throw new GeneralException(ErrorStatus._USER_NOT_FOUND);
            }
            //일반 로그인인 경우
            else {
                user = userRepository.save(User.signUp(request.getProviderId()));
                user.setIsNewUser(false);
                user.setPlatform(Constants.PLATFORM_GENERAL);

            }
        }else {
            // 카카오 로그인 사용자
            user = where_user.get();
        }

        user.setEmail(request.getEmail());
        user.setPassWord(passwordEncoder.encode(request.getPassword()));


        JwtTokenDto jwtTokenDto = jwtUtil.generateTokens(user.getId(), ERole.USER);

        user.setRefreshToken(jwtTokenDto.refreshToken());

        return jwtTokenDto;
    }

    public JwtTokenDto signIn(SignInRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new GeneralException(ErrorStatus._USER_NOT_FOUND));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassWord())) {
            throw new GeneralException(ErrorStatus.NOT_FOUND_PASSWORD);
        }

        JwtTokenDto jwtTokenDto = jwtUtil.generateTokens(user.getId(), ERole.USER);

        if (!jwtTokenDto.refreshToken().equals(user.getRefreshToken())) {
            userRepository.updateRefreshTokenAndLoginStatus(user.getId(), jwtTokenDto.refreshToken(), true);
        }

        return jwtTokenDto;
    }
}
