package com.bjit.tss.repository;

import com.bjit.tss.entity.ExamineeInfo;
import com.bjit.tss.model.ApiResponse;
import com.bjit.tss.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExamineeRepository extends JpaRepository<ExamineeInfo, Long> {


    Optional<ExamineeInfo> findByUserInfoUserIdAndCourseInfoCourseId(Long userId, Long courseId);
    Optional<List<ExamineeInfo>> findByRoleAndCourseInfoIsAvailableAndCourseInfoBatchCode(Role role, Boolean isAvailable, String batchCode);


    List<ExamineeInfo> findByRole(Role role);
}
