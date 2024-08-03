package com.example.block.service;

import com.example.block.converter.HomeRequestConverter;
import com.example.block.domain.User;
import com.example.block.dto.HomeRequestDTO;
import com.example.block.repository.ContestRepository;
import com.example.block.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<User> user = userRepository.findById(userId);
        HomeRequestDTO.HomePageRequestDTO homePageRequestDTO = HomeRequestConverter.toHomePageRequestDTO(contestList, user.orElse(null));
        return homePageRequestDTO;
    }
}
