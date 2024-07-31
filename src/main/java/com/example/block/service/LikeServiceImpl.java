package com.example.block.service;

import com.example.block.converter.LikeConverter;
import com.example.block.domain.mapping.Likes;
import com.example.block.domain.mapping.Matches;
import com.example.block.global.apiPayload.code.status.ErrorStatus;
import com.example.block.global.apiPayload.exception.handler.EmailHandler;
import com.example.block.global.apiPayload.exception.handler.UserHandler;
import com.example.block.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeServiceImpl implements LikeService{
    private final MatchesRepository matchRepository;
    private final ApplicantRepository applicantRepository;
    private final LikesRepository likeRepository;
    private final UserRepository userRepository;
    private final ContestRepository contestRepository;

    public Boolean hasMatched(Integer userId1, Integer applicantId, Integer contestId){
        Integer userId2 = getUser(applicantId);
        if(matchRepository.existsByUserId1AndUserId2AndContestId(userId1, userId2, contestId) || matchRepository.existsByUserId1AndUserId2AndContestId(userId2, userId1, contestId))
            return true;
        else
            return false;
    }
    public Boolean hasLiked(Integer userId1, Integer applicantId, Integer contestId){
        Integer userId2 = getUser(applicantId);
        return likeRepository.existsByUserLikerIdAndUserLikedIdAndContestId(userId2, userId1, contestId);
    }

    public Integer getUser(Integer applicantId){
        Integer userId = applicantRepository.getUserIdByapplicantId(applicantId);
        if (userId == null) {
            throw new UserHandler(ErrorStatus.USERID_NOT_FOUND);
        }
        return userId;
    }
    public String getEmail(Integer userId){
        String email = userRepository.getEmailById(userId);
        if (email == null) {
            throw new EmailHandler(ErrorStatus.EMAIL_NOT_FOUND);
        }
        return email;
    }

    public void likeUser(Integer userId, Integer applicantId, Integer contestId){
        Integer userId2 = getUser(applicantId);
        Likes newLike = LikeConverter.likeDTO(userRepository.findById(userId).get(),
                                               userRepository.findById(userId2).get(),
                                                contestRepository.findById(contestId).get()
                                                );

        likeRepository.save(newLike);
    }
    public void match(Integer userId, Integer applicantId, Integer contestId){
        Integer userId2 = getUser(applicantId);
        Matches newMatch = LikeConverter.matchDTO(userRepository.findById(userId).get(),
                                                    userRepository.findById(userId2).get(),
                                                    contestRepository.findById(contestId).get());

        matchRepository.save(newMatch);
    }
    public Likes deleteLike(Integer userId, Integer applicantId, Integer contestId){
        Integer userId2 = getUser(applicantId);
        Likes like = LikeConverter.likeDTO(userRepository.findById(userId).get(),
                                                userRepository.findById(userId2).get(),
                                                 contestRepository.findById(contestId).get());
        likeRepository.delete(like);
        return like;
    }
}
