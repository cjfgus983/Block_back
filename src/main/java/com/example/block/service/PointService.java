package com.example.block.service;


import com.example.block.domain.PointDetail;


import java.util.List;

public interface PointService {
    public PointDetail chargePoint(Integer userId, Long point);
    public PointDetail usePoint(Integer userId, Long point);
    public Long getMyPoint(Integer userId);
    public List<PointDetail> getMyPointDetail(Integer userId);

    public void payForReview(Integer userId, Integer reviewId,Long point);
    public boolean isAlreadyPaid(Integer userId, Integer reviewId);

}
