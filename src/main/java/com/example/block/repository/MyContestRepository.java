package com.example.block.repository;

import com.example.block.domain.User;
import com.example.block.dto.MyContestResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyContestRepository extends JpaRepository<MyContestResponseDTO.MyContestListDTO, Integer> {
    MyContestResponseDTO.MyContestListDTO findByUserId(Integer userId);
}
