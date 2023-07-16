package com.bjit.tss.repository;

import com.bjit.tss.entity.WrittenMarks;
import com.bjit.tss.entity.WrittenQuestionMarks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WrittenMarksRepository extends JpaRepository<WrittenMarks, Long> {

}