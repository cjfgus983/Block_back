package com.example.block.controller;

import com.example.block.ApiResponse;

import com.example.block.converter.MyPageConverter;
import com.example.block.converter.PointConverter;
import com.example.block.domain.MyContest;
import com.example.block.domain.PointDetail;
import com.example.block.domain.User;
import com.example.block.domain.enums.LoginType;
import com.example.block.domain.mapping.Applicant;
import com.example.block.dto.MyPageResponseDTO;
import com.example.block.dto.PointRequestDTO;
import com.example.block.dto.PointResponseDTO;
import com.example.block.service.AuthService;
import com.example.block.service.MyPageService;
import com.example.block.service.PointService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MyPageController {
    private final PointService pointService;
    private final MyPageService myPageService;
    private final AuthService authService;

    @GetMapping("/point")
    @Operation(summary = "내 포인트 조회")
    public ApiResponse<PointResponseDTO.GetMyPointDTO> getPoint(@PathVariable(name = "userId") Integer userId) {
        //내 포인트 조회
        return ApiResponse.onSuccess(PointConverter.toPointDTO(pointService.getMyPoint(authService.getUserIdFromSecurity())));
    }

    @GetMapping("/{userId}/pointDetail")
    @Operation(summary = "내 포인트 상세 내역 조회")
    public ApiResponse<PointResponseDTO.GetMyPointDetailListDTO> getPointDetail(@PathVariable(name = "userId") Integer userId) {
        //내 포인트 상세 조회 : 최근 5개
        List<PointDetail> pointDetails = pointService.getMyPointDetail(userId);

        return ApiResponse.onSuccess(PointConverter.toPointDetailListDTO(pointDetails));
    }

    @PostMapping("/{userId}/charge")
    @Operation(summary = "포인트 지급")
    public ApiResponse<PointResponseDTO.GetMyPointDetailDTO> chargePoint(@PathVariable(name = "userId") Integer userId,
                                                                         @RequestBody PointRequestDTO.PointCharge request) {
        //포인트 충전
        PointDetail point = pointService.chargePoint(userId, request.getPoint());
        return ApiResponse.onSuccess(PointConverter.toPointDetailDTO(point));
    }

    //  내가 좋아요 누른 사람 목록
    //  토큰 구현되면 userId를 토큰 정보로 대체
    @GetMapping("/like")
    public ApiResponse<List<MyPageResponseDTO.likeListResultDTO>> likeList(@RequestParam(name = "userId") Integer userId) {

        List<Applicant> applicantList = myPageService.getLikeChallengerList(userId);
        return ApiResponse.onSuccess(MyPageConverter.toLikeListResultDTO(applicantList));
    }

    //  나에게 좋아요를 누른 사람 목록
    //  토큰 구현되면 userId를 토큰 정보로 대체
    @GetMapping("/likeBy")
    public ApiResponse<List<MyPageResponseDTO.likeListResultDTO>> likeByList(@RequestParam(name = "userId") Integer userId) {

        List<Applicant> applicantList = myPageService.getLikeByChallengerList(userId);
        return ApiResponse.onSuccess(MyPageConverter.toLikeListResultDTO(applicantList));
    }

    // 마이 페이지 메인 화면
    @GetMapping("{userId}")
    @Operation(summary = "마이 페이지 메인 화면")
    public ApiResponse<MyPageResponseDTO.myPageDTO> getMyPageMain(@RequestParam(name = "userId") Integer userId) {
        // 마이 페이지 메인 화면 데이터 조회
        MyPageResponseDTO.myPageDTO user = myPageService.getMyPageUser(userId);

        return ApiResponse.onSuccess(user);
    }

    // 마이 페이지_내 정보 수정
    @GetMapping("{userId}/edit")
    @Operation(summary = "마이 페이지_내 정보 수정")
    public ApiResponse<MyPageResponseDTO.myPageDTO> editMyPage(@RequestParam(name = "userId") Integer userId) {
        // 마이 페이지 내 정보 수정
        // 기본 정보는 메인 화면과 똑같이 보여주면서 수정 가능한 리스트의 뷰를 보여주므로 같은 메소드 사용
        MyPageResponseDTO.myPageDTO user = myPageService.getMyPageUser(userId);
        return ApiResponse.onSuccess(user);
    }

    // 마이 페이지_내 정보 수정 완료시
    @PostMapping("{userId}/edit")
    @Operation(summary = "마이 페이지_내 정보 수정 완료")
    // 수정된 정보를 확인하기 위해 User를 반환함
    public void editMyPageComplete(@RequestParam(name = "userId") Integer userId,
                                                @RequestBody MyPageResponseDTO.myPageEditDataDTO updatedUser) {
        // 수정된 정보를 저장하고
        myPageService.updateUser(userId, updatedUser);
    }

    // 마이페이지 옵션 내 저장한 공모전, 후기 조회
    @GetMapping("myContestInfo")
    @Operation(summary = "마이페이지 옵션 내 저장한 공모전, 후기 조회")
    public ApiResponse<MyPageResponseDTO.myPageDTO> getMyContest(@RequestParam(name = "userId") Integer userId) {
        // 해당 버튼을 가지고 있는 뷰를 보여주면서 메인페이지도 보여줘야하기 때문에 같은 메소드 사용
        MyPageResponseDTO.myPageDTO user = myPageService.getMyPageUser(userId);
        return ApiResponse.onSuccess(user);
    }

    // 마이페이지 옵션 내 저장한 공모전 조회
    @GetMapping("myContests")
    @Operation(summary = "마이페이지 옵션 내 저장한 공모전 조회")
    public ApiResponse<List<MyPageResponseDTO.contestDTO>> getMyContests(@RequestParam(name = "userId") Integer userId) {
        // 내가 저장한 공모전 목록 조회
        return ApiResponse.onSuccess(myPageService.getMyContestList(userId));
    }
}
