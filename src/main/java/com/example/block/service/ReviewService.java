package com.example.block.service;

import com.example.block.domain.mapping.Review;

public interface ReviewService {
    public Review getReviewDetail(Integer userId,Integer reviewId);

}
