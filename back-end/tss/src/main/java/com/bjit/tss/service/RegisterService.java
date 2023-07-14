package com.bjit.tss.service;

import com.bjit.tss.model.EvaluatorRegisterRequest;
import com.bjit.tss.model.RegisterRequest;
import com.bjit.tss.model.ApiResponse;
import com.bjit.tss.model.ValidationRequest;
import org.springframework.http.ResponseEntity;

public interface RegisterService {
    ResponseEntity<ApiResponse<?>> applicantRegistration(RegisterRequest registerRequest);
    void adminRegistration(String email, String password);

    ResponseEntity<ApiResponse<?>> mailValidation(ValidationRequest validationRequest);

    ResponseEntity<ApiResponse<?>> evaluatorRegistration(EvaluatorRegisterRequest evaluatorRegisterRequest);
}
