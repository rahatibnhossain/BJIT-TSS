package com.bjit.tss.service;

import com.bjit.tss.model.RegisterRequest;
import com.bjit.tss.model.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface RegisterService {
    ResponseEntity<ApiResponse<?>> applicantRegistration(RegisterRequest registerRequest);
}
