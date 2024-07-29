package com.example.block.repository;

import com.example.block.domain.mapping.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Integer> {
    Optional<Likes> findByUserLikerIdAndUserLikedIdAndContestId(Long userLikerId, Long userLikedId, Integer contestId);
    List<Likes> findByUserLikerId(Long userLikerId);
}
