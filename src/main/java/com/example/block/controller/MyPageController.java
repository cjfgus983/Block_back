package com.example.block.controller;

import com.example.block.ApiResponse;
import com.example.block.converter.PointConverter;
import com.example.block.domain.PointDetail;
import com.example.block.service.DTO.PointRequestDTO;
import com.example.block.service.DTO.PointResponseDTO;
import com.example.block.service.PointService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MyPageController {
    private final PointService pointService;

    @GetMapping("/{userId}/point")
    @Operation(summary = "내 포인트 조회")
    public ApiResponse<PointResponseDTO.GetMyPointDTO> getPoint(@PathVariable(name="userId") Long userId) {
        //내 포인트 조회
        return ApiResponse.onSuccess(PointConverter.toPointDTO(pointService.getMyPoint(userId)));
    }

    @GetMapping("/{userId}/pointDetail")
    @Operation(summary = "내 포인트 상세 내역 조회")
    public ApiResponse<PointResponseDTO.GetMyPointDetailListDTO> getPointDetail(@PathVariable(name="userId") Long userId) {
        //내 포인트 상세 조회 : 최근 5개
        List<PointDetail> pointDetails=pointService.getMyPointDetail(userId);

        return ApiResponse.onSuccess(PointConverter.toPointDetailListDTO(pointDetails));
    }

    @PostMapping("/{userId}/charge")
    @Operation(summary = "포인트 지급")
    public ApiResponse<PointResponseDTO.GetMyPointDetailDTO> chargePoint(@PathVariable(name="userId") Long userId,
                                                                         @RequestBody PointRequestDTO.PointCharge request) {
        //포인트 충전
        PointDetail point=pointService.chargePoint(userId,request.getPoint());
        return ApiResponse.onSuccess(PointConverter.toPointDetailDTO(point));
    }

}
