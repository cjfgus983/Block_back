package com.example.block.converter;

import com.example.block.domain.mapping.Applicant;
import com.example.block.dto.TeamMatchResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class TeamMatchConverter {

    public static List<TeamMatchResponseDTO.ChallengerListResultDTO> toChallengerListResultDTO(List<Applicant> applicantList){

        return applicantList.stream()
                .map(applicant -> TeamMatchResponseDTO.ChallengerListResultDTO.builder()
                        .name(applicant.getUser().getName())
                        .university(applicant.getUser().getUniversity())
                        .applyPart(applicant.getApplyPart())
                        .build()).collect(Collectors.toList());
    }
}
