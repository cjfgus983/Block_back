package com.example.block.converter;

import com.example.block.domain.Contest;
import com.example.block.dto.ContestResponseDTO;

public class ContestConverter {
    public static ContestResponseDTO.ContestInfoDTO toContestInfoDTO(Contest contest) {
        return ContestResponseDTO.ContestInfoDTO.builder()
                .contestId(contest.getId())
                .contestName(contest.getTitle())
                .contestImage(contest.getImageUrl())
//                .contestCategory(contest.getCategory())
                .startDate(contest.getStartDate().toString())
                .endDate(contest.getEndDate().toString())
                .build();
    }
}
