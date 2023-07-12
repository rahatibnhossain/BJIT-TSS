package com.bjit.tss.repository;

import com.bjit.tss.entity.ExamineeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExamineeRepository extends JpaRepository<ExamineeInfo, Long> {


    Optional<ExamineeInfo> findByUserInfoUserIdAndCourseInfoCourseId(Long userId, Long courseId);
}
