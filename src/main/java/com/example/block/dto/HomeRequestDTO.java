package com.example.block.dto;

import com.example.block.domain.Contest;
import lombok.*;

import java.util.List;

public class HomeRequestDTO {

    @Builder
    @Getter
    @Setter
    @RequiredArgsConstructor
    @AllArgsConstructor
    public static class HomePageRequestDTO {
        private String userName;

        private List<HomeContestDTO> contestList;

    }

    @Builder
    @Getter
    @Setter
    @RequiredArgsConstructor
    @AllArgsConstructor
    public static class HomeContestDTO {
        private Integer contestId;
        private String contestName;
        private String contestHost;

        private String contestCategory;
        private String contestImage;
    }
}
