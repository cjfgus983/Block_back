package com.example.block.service;

import com.example.block.converter.ContestConverter;
import com.example.block.domain.Contest;
import com.example.block.dto.ContestResponseDTO;
import com.example.block.dto.MyContestResponseDTO;
import com.example.block.repository.ContestRepository;
import com.example.block.repository.MyContestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContestService {
    private final ContestRepository contestRepository;
    private final MyContestRepository myContestRepository;

    public ContestResponseDTO.ContestInfoDTO getContestInfo(Integer contestId) {
        Contest contest = contestRepository.findById(contestId).orElseThrow(() -> new IllegalArgumentException("해당 대회가 존재하지 않습니다."));
        ContestResponseDTO.ContestInfoDTO contestInfoDTO = ContestConverter.toContestInfoDTO(contest);
        return contestInfoDTO;
    }

    // 공모전 링크를 추출해주는 메소드
    public String getContestApplyUrl(Integer contestId) {
        Contest contest = contestRepository.findById(contestId).orElseThrow(() -> new IllegalArgumentException("해당 대회가 존재하지 않습니다."));
        return contest.getApplyUrl(); //
    }

    // 공모전을 저장하는 메소드
//    public void saveMyContest(Integer contestId, Integer userId) {
//        Contest contest = contestRepository.findById(contestId).orElseThrow(() -> new IllegalArgumentException("해당 대회가 존재하지 않습니다."));
//        ContestResponseDTO.ContestInfoDTO contestInfoDTO = ContestConverter.toContestInfoDTO(contest);
//        contestInfoDTO.setMyContestId();
//    }
}
