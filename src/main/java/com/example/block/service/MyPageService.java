package com.example.block.service;

import com.example.block.domain.mapping.Applicant;
import com.example.block.domain.mapping.Likes;
import com.example.block.repository.ApplicantRepository;
import com.example.block.repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final LikesRepository likesRepository;
    private final ApplicantRepository applicantRepository;

    public Applicant getChallenger(Integer contestId, Long userId){
        return applicantRepository.findByContestIdAndUserId(contestId, userId).get();
    }

    public List<Applicant> getLikeChallengerList(Long userId){
        List<Likes> likeList = likesRepository.findByUserLikerId(userId);

        return likeList.stream()
                .map(like -> getChallenger(like.getContest().getId(), like.getUserLiked().getId()))
                .collect(Collectors.toList());
    }
}
