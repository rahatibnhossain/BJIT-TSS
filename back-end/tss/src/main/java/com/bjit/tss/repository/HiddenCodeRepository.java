package com.bjit.tss.repository;

import com.bjit.tss.entity.CandidateMarks;
import com.bjit.tss.entity.HiddenCodeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface HiddenCodeRepository extends JpaRepository<HiddenCodeInfo, Long> {



    Optional<HiddenCodeInfo> findByCandidateMarksCandidateId(Long candidateId);
}

