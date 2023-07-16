package com.bjit.tss.service;

import com.bjit.tss.model.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface ApplicantDashboardService {
    ResponseEntity<ApiResponse<?>> getApplicantDashboardData();
}
