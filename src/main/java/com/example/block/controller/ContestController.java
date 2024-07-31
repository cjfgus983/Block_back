package com.example.block.controller;

import com.example.block.ApiResponse;
import com.example.block.dto.ContestResponseDTO;
import com.example.block.service.ContestService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contest")
public class ContestController {
    private final ContestService contestService;

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
    public ApiResponse<String> saveContest(@PathVariable Integer contestId, @RequestParam Integer userId) {
        contestService.saveMyContest(contestId, userId);
        return ApiResponse.onSuccess("공모전 저장 완료");
    }
}
