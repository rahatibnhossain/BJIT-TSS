package com.bjit.tss.service;

import com.bjit.tss.model.LoginRequest;
import com.bjit.tss.model.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface LoginService {

    ResponseEntity<ApiResponse<?>> login(LoginRequest loginRequest);
}