package com.example.block.controller;

import com.example.block.ApiResponse;
import com.example.block.domain.Contest;
import com.example.block.domain.MyContest;
import com.example.block.dto.ContestResponseDTO;
import com.example.block.repository.ContestRepository;
import com.example.block.repository.MyContestRepository;
import com.example.block.service.ContestService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contest")
public class ContestController {
    private final ContestService contestService;
    private final MyContestRepository myContestRepository;

    // 공모전 상세 페이지
    @GetMapping("{contestId}")
    @Operation(summary = "공모전 상세 페이지")
    public ContestResponseDTO.ContestInfoDTO getContestInfo(@PathVariable Integer contestId) {
        return contestService.getContestInfo(contestId);
    }

    // 공모전 링크로 이동
    // TODO: 공모전 링크로 이동하는 리다이렉션 구현.
    @GetMapping("{contestId}/apply")
    @Operation(summary = "공모전 링크로 이동")
    public ApiResponse<String> getContestApplyUrl(@PathVariable Integer contestId) {
        return ApiResponse.onSuccess(contestService.getContestApplyUrl(contestId));
    }

    // 공모전 저장
    @PostMapping("{contestId}/save")
    @Operation(summary = "공모전 저장")
    public ApiResponse<MyContest> saveContest(@PathVariable Integer contestId, @RequestParam Integer userId) {
        contestService.saveMyContest(contestId, userId);

        // 똑바로 들어갔는지 확인하기 위함(테스트용)
        MyContest contest = myContestRepository.findByUserIdAndId(userId, contestId);
        return ApiResponse.onSuccess(contest);
    }
}
