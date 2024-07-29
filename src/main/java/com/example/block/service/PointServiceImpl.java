package com.example.block.service;

import com.example.block.converter.PointConverter;
import com.example.block.converter.TransactionReviewConverter;
import com.example.block.domain.PointDetail;
import com.example.block.domain.User;
import com.example.block.domain.enums.PointType;
import com.example.block.domain.mapping.Review;
import com.example.block.domain.mapping.TransactionReview;
import com.example.block.global.apiPayload.code.status.ErrorStatus;
import com.example.block.global.apiPayload.exception.GeneralException;
import com.example.block.repository.PointDetailRepository;
import com.example.block.repository.ReviewRepository;
import com.example.block.repository.TransactionReviewRepository;
import com.example.block.repository.UserRepository;
import com.example.block.service.DTO.PointResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PointServiceImpl implements PointService {

    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final TransactionReviewRepository transactionReviewRepository;
    private final PointDetailRepository pointDetailRepository;

    @Override
    @Transactional
    public PointDetail chargePoint(Long userId, Long point) {
        //포인트 충전
        User user = userRepository.findById(userId).orElseThrow(
                () -> new GeneralException(ErrorStatus._USER_NOT_FOUND));

        user.chargePoint(point);
        //pointDetail 추가
        PointDetail pointDetail = PointConverter.toPointDetail(point,PointType.EARN,"리워드 지급");
        pointDetail.setUser(user);
        return pointDetail;
    }

    @Override
    @Transactional
    public PointDetail usePoint(Long userId, Long point) {
        //포인트 사용
        User user = userRepository.findById(userId).orElseThrow(
                () -> new GeneralException(ErrorStatus._USER_NOT_FOUND));

        user.usePoint(point);
        //pointDetail 추가
        PointDetail pointDetail = PointConverter.toPointDetail(point,PointType.SPEND,"포인트 사용");
        pointDetail.setUser(user);
        return pointDetail;
    }

    @Override
    public Long getMyPoint(Long userId) {
        //내 포인트 조회
        User user = userRepository.findById(userId).orElseThrow(
                () -> new GeneralException(ErrorStatus._USER_NOT_FOUND));

        return user.getPoint();
    }

    @Override
    public List<PointDetail> getMyPointDetail(Long userId) {
        //내 포인트 상세 조회
        User user = userRepository.findById(userId).orElseThrow(
                () -> new GeneralException(ErrorStatus._USER_NOT_FOUND));

        Pageable pageable = PageRequest.of(0, 5);

        return pointDetailRepository.findTopByUserIdOrderByCreatedAtDesc(userId,pageable);
    }

    @Override
    @Transactional
    public void payForReview(Long userId, Long reviewId, Long point){
        //리뷰 정상 결제 시 transactionReview 추가 및 pointDetail 추가
        User user = userRepository.findById(userId).orElseThrow(
                () -> new GeneralException(ErrorStatus._USER_NOT_FOUND));
        Review review=reviewRepository.findById(reviewId).orElseThrow(
                () -> new GeneralException(ErrorStatus._REVIEW_NOT_FOUND));

        //포인트 사용
        user.usePoint(point);

        //transactionReview 추가
        TransactionReview transactionReview = TransactionReviewConverter.toTransactionReview(user,review);
        transactionReview.setUserReview(user,review);

        //pointDetail 추가
        PointDetail pointDetail = PointConverter.toPointDetail(point,PointType.KAKAOPAYSPEND,"공모전 후기 결제");
        pointDetail.setUser(user);

    }

    @Override
    @Transactional
    public boolean isAlreadyPaid(Long userId, Long reviewId) {
        //이미 결제한 리뷰인지 확인
        User user = userRepository.findById(userId).orElseThrow(
                () -> new GeneralException(ErrorStatus._USER_NOT_FOUND));
        Review review=reviewRepository.findById(reviewId).orElseThrow(
                () -> new GeneralException(ErrorStatus._REVIEW_NOT_FOUND));

        TransactionReview transactionReview = transactionReviewRepository.findAllByUserAndReview(user,review);

        return transactionReview != null;
    }

}
