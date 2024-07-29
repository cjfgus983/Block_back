package com.example.block.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.block.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
}
