package com.example.block.service;

import com.example.block.domain.mapping.Likes;

public interface LikeService {
    Integer getUser(Integer applicantId);
    Boolean hasMatched(Integer userId1, Integer userId2, Integer contestId);
    Boolean hasLiked(Integer userId1, Integer userId2, Integer contestId);
    String getEmail(Integer userId);
    void likeUser(Integer userId, Integer applicantId, Integer contestId);
    void match(Integer userId, Integer applicantId, Integer contestId);
    Likes deleteLike(Integer userId, Integer applicantId, Integer contestId);

}
