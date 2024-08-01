package com.example.block.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Map;

@Entity
@Table(name = "mycontest")
@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class MyContest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    @ElementCollection
    @CollectionTable(name = "contest_info_map", joinColumns = @JoinColumn(name = "my_contest_id"))
    @MapKeyJoinColumn(name = "contest_info_id")
    @Column(name = "contest_number")
    private Map<Contest, Integer> myContestList;
}