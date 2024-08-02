package com.example.block.service;

import com.example.block.converter.HomeRequestConverter;
import com.example.block.domain.User;
import com.example.block.dto.HomeRequestDTO;
import com.example.block.repository.ContestRepository;
import com.example.block.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomePageService {
    private final ContestRepository contestRepository;
    private final UserRepository userRepository;

    public List<HomeRequestDTO.HomeContestDTO> getAllContestList() {
        return HomeRequestConverter.toHomeContestDTOList(contestRepository.findAll());
    }

    public HomeRequestDTO.HomePageRequestDTO getHomePageRequestDTO(Integer userId) {
        List<HomeRequestDTO.HomeContestDTO> contestList = getAllContestList();
        User user = userRepository.findByUserId(userId);
        HomeRequestDTO.HomePageRequestDTO homePageRequestDTO = HomeRequestConverter.toHomePageRequestDTO(contestList, user);
        return homePageRequestDTO;
    }
}
