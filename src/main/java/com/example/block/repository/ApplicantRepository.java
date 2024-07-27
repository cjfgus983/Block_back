package com.example.block.repository;

import com.example.block.domain.mapping.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

    List<Applicant> findByContestId(Long contestId);
}
