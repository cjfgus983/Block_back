package com.example.block.service;

import com.example.block.domain.mapping.Applicant;
import com.example.block.repository.ApplicantRepository;
import com.example.block.repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamMatchService {

    private final ApplicantRepository applicantRepository;
    private final LikesRepository likesRepository;

    public List<Applicant> getChallengerList(Long contestId){
        return applicantRepository.findByContestId(contestId);
    }

    public Applicant getChallenger(Long challengerId){
        return applicantRepository.findById(challengerId).get();
    }

    public Boolean hasUserLiked(Long userLikerId, Long userLikedId, Long contestId){
        return likesRepository.findByUserLikerIdAndUserLikedIdAndContestId(userLikerId, userLikedId, contestId).isPresent();
    }
}
