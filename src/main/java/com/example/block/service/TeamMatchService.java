package com.example.block.service;

import com.example.block.domain.mapping.Applicant;
import com.example.block.repository.ApplicantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamMatchService {

    private final ApplicantRepository applicantRepository;

    public List<Applicant> getChallengerList(Long contestId){
        return applicantRepository.findByContestId(contestId);
    }
}
