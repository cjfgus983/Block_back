package com.example.block.service;


import com.example.block.domain.User;
import com.example.block.domain.mapping.Review;
import com.example.block.domain.mapping.TransactionReview;
import com.example.block.global.apiPayload.code.status.ErrorStatus;
import com.example.block.global.apiPayload.exception.GeneralException;
import com.example.block.repository.ReviewRepository;
import com.example.block.repository.TransactionReviewRepository;
import com.example.block.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService{

    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final TransactionReviewRepository transactionReviewRepository;
    private final PointService pointService;


    @Override
    public Review getReviewDetail(Integer userId,Integer reviewId){
        //리뷰 결제 여부 확인
        if (!pointService.isAlreadyPaid(userId,reviewId)) {
            throw new GeneralException(ErrorStatus._NEED_PAY);
        }

        Review review=reviewRepository.findById(reviewId).orElseThrow(
                () -> new GeneralException(ErrorStatus._REVIEW_NOT_FOUND));

        // 리뷰 반환
        return review;
    }
}
