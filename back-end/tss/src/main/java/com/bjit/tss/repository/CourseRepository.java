package com.bjit.tss.repository;

import com.bjit.tss.entity.CourseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CourseRepository extends JpaRepository<CourseInfo, Long> {
    Optional<CourseInfo> findByBatchCode(String batchCode);

}

