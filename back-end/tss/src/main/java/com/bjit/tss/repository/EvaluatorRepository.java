package com.bjit.tss.repository;

import com.bjit.tss.entity.CourseInfo;
import com.bjit.tss.entity.EvaluatorInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface EvaluatorRepository extends JpaRepository<EvaluatorInfo, Long> {

}

