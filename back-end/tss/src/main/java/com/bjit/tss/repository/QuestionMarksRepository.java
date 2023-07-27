package com.bjit.tss.repository;

import com.bjit.tss.entity.QuestionMarks;
import com.bjit.tss.entity.RoundMarks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionMarksRepository extends JpaRepository<QuestionMarks, Long> {

}