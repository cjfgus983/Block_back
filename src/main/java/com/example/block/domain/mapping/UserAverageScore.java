//package com.example.block.domain.mapping;
//
//import com.example.block.domain.common.BaseEntity;
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity(name = "UserAverageScore")
//@Table(indexes = {
//        @Index(name = "contest_id_idx", columnList = "contest_id, id"),
//        @Index(name = "contest_user_idx", columnList = "contest_id, user_id")})
//@Getter
//@Builder
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@AllArgsConstructor
//public class UserAverageScore extends BaseEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//    @OneToMany(mappedBy = "userAverageScore", cascade = CascadeType.ALL)
//    private List<TransactionReview> transactionReviewList=new ArrayList<>();
//}
