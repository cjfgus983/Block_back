package com.example.block.service;


import com.example.block.domain.PointDetail;


import java.util.List;

public interface PointService {
    public PointDetail chargePoint(Long userId, Long point);
    public PointDetail usePoint(Long userId, Long point);
    public Long getMyPoint(Long userId);
    public List<PointDetail> getMyPointDetail(Long userId);

    public void payForReview(Long userId, Long reviewId,Long point);
    public boolean isAlreadyPaid(Long userId, Long reviewId);

}
