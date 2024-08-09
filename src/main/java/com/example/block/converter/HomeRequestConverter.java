package com.example.block.converter;

import com.example.block.domain.Contest;
import com.example.block.domain.User;
import com.example.block.dto.HomeRequestDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

public class HomeRequestConverter {
    public static HomeRequestDTO.HomePageRequestDTO toHomePageRequestDTO(@RequestBody List<HomeRequestDTO.HomeContestDTO> contestList, User user) {
            return HomeRequestDTO.HomePageRequestDTO.builder()
                    .userName(user.getName())
                    .userImageUrl(user.getImageUrl())
                .contestList(contestList)
                .build();
    }

    public static HomeRequestDTO.HomeContestDTO toHomeContestDTO(@RequestBody Contest contest) {
        return HomeRequestDTO.HomeContestDTO.builder()
                .contestId(contest.getId())
                .contestName(contest.getTitle())
                .contestHost(contest.getHost())
                .contestImageUrl(contest.getImageUrl())
                .build();
    }

    public static List<HomeRequestDTO.HomeContestDTO> toHomeContestDTOList(@RequestBody List<Contest> contests) {
        return contests.stream()
                .map(HomeRequestConverter::toHomeContestDTO)
                .collect(Collectors.toList());
    }




}
