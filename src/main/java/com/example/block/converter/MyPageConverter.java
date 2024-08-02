package com.example.block.converter;

import com.example.block.domain.User;
import com.example.block.domain.mapping.Applicant;
import com.example.block.dto.MyPageResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class MyPageConverter {

    public static List<MyPageResponseDTO.likeListResultDTO> toLikeListResultDTO(List<Applicant> applicantList){
        return applicantList.stream()
                .map(applicant -> MyPageResponseDTO.likeListResultDTO.builder()
                        .name(applicant.getUser().getName())
                        .university(applicant.getUser().getUniversity())
                        .major(applicant.getUser().getUnivMajor())
                        .applyPart(applicant.getApplyPart())
                        .contestTitle(applicant.getContest().getTitle())
                        .build()
                ).collect(Collectors.toList());
    }

    public static MyPageResponseDTO.changeProfileImageDTO toChangeProfileImageDTO(String fileName){
        return MyPageResponseDTO.changeProfileImageDTO.builder()
                .profileImageName(fileName)
                .build();
    }
}
