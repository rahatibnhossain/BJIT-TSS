package com.bjit.tss.repository;

import com.bjit.tss.entity.CandidateMarks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<CandidateMarks, Long> {

    Optional<CandidateMarks> findByExamineeInfoExamineeId(Long examineeId);
}