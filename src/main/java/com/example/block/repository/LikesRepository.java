package com.example.block.repository;

import com.example.block.domain.mapping.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    Optional<Likes> findByUserLikerIdAndUserLikedIdAndContestId(Long userLikerId, Long userLikedId, Long contestId);
}
