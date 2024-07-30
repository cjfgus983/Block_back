package com.example.block.repository;


import com.example.block.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Modifying
    @Query("UPDATE User u SET u.point = :totalPoints WHERE u.id = :userId")
    void updateUserPoints(@Param("userId") Integer userId, @Param("totalPoints") Long totalPoints);

}
