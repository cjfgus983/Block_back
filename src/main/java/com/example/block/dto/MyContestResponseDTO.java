package com.example.block.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyContestResponseDTO {

    // Contest 저장 화면에서 사용할 DTO
    @Builder
    @Getter
    @Setter
    @RequiredArgsConstructor
    @AllArgsConstructor
    public static class MyContestListDTO {
        Integer userId;
        Map<ContestResponseDTO.ContestInfoDTO, Integer> MyContestList;
    }
}
