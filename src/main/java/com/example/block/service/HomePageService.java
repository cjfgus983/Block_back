package com.example.block.service;

import com.example.block.converter.HomeRequestConverter;
import com.example.block.dto.HomeRequestDTO;
import com.example.block.repository.ContestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomePageService {
    private final ContestRepository contestRepository;

    public List<HomeRequestDTO.HomeContestDTO> getAllContestList() {
        return HomeRequestConverter.toHomeContestDTOList(contestRepository.findAll());
    }

    public HomeRequestDTO.HomePageRequestDTO getHomePageRequestDTO() {
        List<HomeRequestDTO.HomeContestDTO> contestList = getAllContestList();
        return HomeRequestDTO.HomePageRequestDTO.builder()
                .contestList(contestList)
                .build();
    }
}
