package com.bjit.tss.service;

import com.bjit.tss.model.ApiResponse;
import com.bjit.tss.model.ApplicationRequest;
import com.bjit.tss.model.CourseRoleRequest;
import org.springframework.http.ResponseEntity;

public interface ApplicationService {

    ResponseEntity<ApiResponse<?>> applyCourse(ApplicationRequest applicationRequest);

    ResponseEntity<ApiResponse<?>> allApplicationSpecific(CourseRoleRequest courseRoleRequest);

    ResponseEntity<ApiResponse<?>> allUnassignedCandidates(CourseRoleRequest courseRoleRequest);
}
