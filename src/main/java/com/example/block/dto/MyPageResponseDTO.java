package com.example.block.dto;

import com.example.block.domain.enums.ApplyPart;
import com.example.block.domain.enums.ContestType;
import com.example.block.domain.enums.LoginType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    }

    // mypage 메인화면에서 활용
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class myPageDTO {
        String name;
        String imageUrl;
        String university;
        String major;
        String category; // 추후 추가
        LoginType loginType;
    }

    // mypage 프로필 수정에서 사용
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class myPageEditDataDTO {
        Integer userId;
        String  passWord;
        String  birthDay;
        String  phoneNumber;
        String  address;
        String  university;
        String  univMajor;
        String  portfolio;
        String  category; // 추후 추가
    }

    // 공모전 목록 조회에서 사용
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class contestDTO {
        Integer myContestId;

        Integer id;
        String applyUrl;
        String imageUrl;
        ContestType status;
    }
}
