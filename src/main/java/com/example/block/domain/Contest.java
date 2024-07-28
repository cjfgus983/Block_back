package com.example.block.domain;

import com.example.block.domain.common.BaseEntity;
import com.example.block.domain.enums.ContestType;
import com.example.block.domain.mapping.Applicant;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Contest")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicInsert
public class Contest extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(nullable = true)
    private String url;

    @Column(nullable = true)
    private String hashtag;

    @Column(nullable = false, length = 10)
    private String startDate;

    @Column(nullable = false, length = 10)
    private String endDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @ColumnDefault("'ACTIVE'")
    private ContestType contestType;

    @OneToMany(mappedBy = "contest")
    private List<Applicant> applicantList = new ArrayList<Applicant>();
}
