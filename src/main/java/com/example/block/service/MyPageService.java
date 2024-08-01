package com.example.block.service;

import com.example.block.converter.MyPageConverter;
import com.example.block.domain.Contest;
import com.example.block.domain.MyContest;
import com.example.block.domain.User;
import com.example.block.domain.mapping.Applicant;
import com.example.block.domain.mapping.Likes;
import com.example.block.dto.MyPageResponseDTO;
import com.example.block.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final LikesRepository likesRepository;
    private final ApplicantRepository applicantRepository;
    private final UserRepository userRepository;
    private final MyContestRepository mycontestRepository;

    public Applicant getChallenger(Integer contestId, Integer userId){
        return applicantRepository.findByContestIdAndUserId(contestId, userId).get();
    }

    //  내가 좋아요 누른 사람 목록
    public List<Applicant> getLikeChallengerList(Integer userId){
        List<Likes> likeList = likesRepository.findByUserLikerId(userId);

        return likeList.stream()
                .map(like -> getChallenger(like.getContest().getId(), like.getUserLiked().getId()))
                .collect(Collectors.toList());
    }

    //  나에게 좋아요를 누른 사람 목록
    public List<Applicant> getLikeByChallengerList(Integer userId){
        List<Likes> likedList = likesRepository.findByUserLikedId(userId);

        return likedList.stream()
                .map(like -> getChallenger(like.getContest().getId(), like.getUserLiker().getId()))
                .collect(Collectors.toList());
    }

    // 마이페이지를 띄워줄 유저 정보
    public MyPageResponseDTO.myPageDTO getMyPageUser(Integer userId){
        User user = userRepository.findByUserId(userId);
        return MyPageConverter.toMyPageDTO(user);
    }

    // 마이페이지를 띄워줄 유저 정보 수정
    public MyPageResponseDTO.myPageEditDataDTO updateUser(Integer userId, MyPageResponseDTO.myPageEditDataDTO updatedUser) {
        User user = userRepository.findByUserId(userId);
        user.setUserId(updatedUser.getUserId());
        user.setPassWord(updatedUser.getPassWord());
        user.setBirthDay(updatedUser.getBirthDay());
        user.setPhoneNumber(updatedUser.getPhoneNumber());
        user.setAddress(updatedUser.getAddress());
        user.setUniversity(updatedUser.getUniversity());
        user.setUnivMajor(updatedUser.getUnivMajor());
        user.setPortfolio(updatedUser.getPortfolio());
//        user.setCategory(updatedUser.getCategory()); // 추후 추가
        userRepository.save(user);
        return MyPageConverter.toMyPageEditDataDTO(user);
    }

    // 저장한 공모전을 모두 조회

    // TODO: 수정
//    public List<MyPageResponseDTO.contestDTO> getMyAllContest(Integer userId) {
//        List<MyContest> contestList = mycontestRepository.findAll();
//        return IntStream.range(0, contestList.size())
//                .mapToObj(index -> {
//                    MyContest mycontest = contestList.get(index);
//                    return MyPageResponseDTO.contestDTO.builder()
//                            .myContestId(index)
//                            .id(mycontest.getId())
////                            .imageUrl(mycontest.getImageUrl())
////                            .applyUrl(mycontest.getApplyUrl())
////                            // .status(contest.getStatus()) // ContestType Enum 추가 후 주석 해제
////                            .build();
//                })
//                .collect(Collectors.toList());
//    }
}
