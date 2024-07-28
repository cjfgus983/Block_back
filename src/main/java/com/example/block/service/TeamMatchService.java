package com.example.block.service;

import com.example.block.domain.Contest;
import com.example.block.domain.User;
import com.example.block.domain.mapping.Applicant;
import com.example.block.dto.TeamMatchRequestDTO;
import com.example.block.repository.ApplicantRepository;
import com.example.block.repository.ContestRepository;
import com.example.block.repository.LikesRepository;
import com.example.block.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamMatchService {

    private final UserRepository userRepository;
    private final ContestRepository contestRepository;
    private final ApplicantRepository applicantRepository;
    private final LikesRepository likesRepository;

    public void applyToContest(TeamMatchRequestDTO.ApplyDTO request, Integer contestId, Long userId){

        User user = userRepository.findById(userId).get();
        Contest contest = contestRepository.findById(contestId).get();

        Applicant newApplicant = Applicant.builder()
                .user(user)
                .contest(contest)
                .applyPart(request.getApplyPart())
                .content(request.getContent())
                .build();

        applicantRepository.save(newApplicant);
    }

    public List<Applicant> getChallengerList(Integer contestId){
        return applicantRepository.findByContestId(contestId);
    }

    public Applicant getChallenger(Integer contestId, Integer challengerId){
        return applicantRepository.findByContestIdAndId(contestId, challengerId);
    }

    public Boolean hasUserLiked(Long userLikerId, Long userLikedId, Integer contestId){
        return likesRepository.findByUserLikerIdAndUserLikedIdAndContestId(userLikerId, userLikedId, contestId).isPresent();
    }
}
