package com.example.block.service;

import com.example.block.domain.Contest;
import com.example.block.domain.User;
import com.example.block.domain.mapping.Rating;
import com.example.block.global.apiPayload.code.status.ErrorStatus;
import com.example.block.global.apiPayload.exception.handler.RatingHandler;
import com.example.block.repository.ContestRepository;
import com.example.block.repository.MatchesRepository;
import com.example.block.repository.RatingRepository;
import com.example.block.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final UserRepository userRepository;
    private final MatchesRepository matchesRepository;
    private final RatingRepository ratingRepository;
    private final ContestRepository contestRepository;

    @Transactional
    public void rateUser(Integer raterId, Integer ratedId, Integer contestId, Float score){

        User rater = userRepository.findById(raterId).get();
        User rated = userRepository.findById(ratedId).get();
        Contest contest = contestRepository.findById(contestId).get();

        Boolean isMatched = matchesRepository.existsByContestIdAndUserIds(contestId, raterId, ratedId);
        if(!isMatched){
            throw new RatingHandler(ErrorStatus.CHALLENGER_NOT_MATCHED);
        }

        Boolean hasRated = ratingRepository.existsByRaterIdAndRatedIdAndContestId(raterId, ratedId, contestId);
        if(hasRated){
            throw new RatingHandler(ErrorStatus.RATING_ALREADY_COMPLETED);
        }

        Rating rating = Rating.builder()
                .rater(rater)
                .rated(rated)
                .contest(contest)
                .score(score)
                .build();

        ratingRepository.save(rating);
    }
}
