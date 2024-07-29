package com.example.block.service.kakao;

import com.example.block.service.DTO.KakaoPayRequestDTO;
import com.example.block.service.DTO.KakaoPayResponseDTO;

public interface KakaoPayService {
    public KakaoPayResponseDTO.KakaoPayReadyResponseDTO kakaoPayReady(KakaoPayRequestDTO.KakaoPayReadyRequestDTO readyDTO);
    public KakaoPayResponseDTO.KakaoPayApproveResponseDTO kakaoPayApprove(String pgToken);

}
