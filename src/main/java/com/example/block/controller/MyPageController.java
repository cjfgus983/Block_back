package com.example.block.controller;

import com.example.block.ApiResponse;
import com.example.block.converter.MyPageConverter;
import com.example.block.domain.mapping.Applicant;
import com.example.block.dto.MyPageResponseDTO;
import com.example.block.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/myPage")
public class MyPageController {

    private final MyPageService myPageService;

    //  토큰 구현되면 userId를 토큰 정보로 대체
    @GetMapping("/like")
    public ApiResponse<List<MyPageResponseDTO.likeListResultDTO>> likeList(@RequestParam(name = "userId") Long userId){

        List<Applicant> applicantList = myPageService.getLikeChallengerList(userId);
        return ApiResponse.onSuccess(MyPageConverter.toLikeListResultDTO(applicantList));
    }

    //  토큰 구현되면 userId를 토큰 정보로 대체
    @GetMapping("/likeBy")
    public void likeByList(@RequestParam(name = "userId") Long userId){}
}
