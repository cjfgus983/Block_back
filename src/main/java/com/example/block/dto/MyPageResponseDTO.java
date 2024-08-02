package com.example.block.dto;

import com.example.block.domain.enums.ApplyPart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MyPageResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class likeListResultDTO {
        String name;
        String university;
        String major;
        ApplyPart applyPart;
        String contestTitle;
//        String profileImageUrl;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class changeProfileImageDTO {
        String profileImageName;
    }

}
