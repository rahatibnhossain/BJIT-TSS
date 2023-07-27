package com.bjit.tss.repository;

import com.bjit.tss.entity.DataStorage;
import com.bjit.tss.entity.RoundMarks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoundMarksRepository extends JpaRepository<RoundMarks, Long> {

}