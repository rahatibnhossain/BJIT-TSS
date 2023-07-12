package com.bjit.tss.service;

import com.bjit.tss.model.ApiResponse;
import com.bjit.tss.model.ApplicationRequest;
import org.springframework.http.ResponseEntity;

public interface ApplicationService {
    ResponseEntity<ApiResponse<?>> applyCourse(ApplicationRequest applicationRequest);
}
