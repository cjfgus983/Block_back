package com.example.block.domain;

import com.example.block.domain.enums.ContestType;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity(name = "Contest")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Contest {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = true)
    private String applyUrl;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String hashTag;

    @Column(nullable = false, length = 20)
    private String startDate;

    @Column(nullable = false, length = 20)
    private String endDate;

//    @Column(nullable = true)
//    @Enumerated(EnumType.STRING)
//    private ContestType contestType;

    @Column(nullable = false)
    private String host;

}
