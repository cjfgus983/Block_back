package com.example.block.repository;

import com.example.block.domain.MyContest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyContestRepository extends JpaRepository<MyContest, Integer> {
    MyContest findByUserId(Integer userId);
    MyContest findByUserIdAndId(Integer userId, Integer Id);
}
