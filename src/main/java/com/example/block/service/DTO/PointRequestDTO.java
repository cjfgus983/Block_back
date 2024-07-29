package com.example.block.service.DTO;

import com.example.block.domain.enums.PointType;
import lombok.Getter;

public class PointRequestDTO {
    @Getter
    public static class PointCharge{
        private Long point;
        private String reason;
    }

    @Getter
    public static class PointUse{
        private Long userId;
        private Long point;
    }
}
