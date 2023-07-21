package com.bjit.tss.controller;

import com.bjit.tss.model.EvaluatorRegisterRequest;
import com.bjit.tss.model.RegisterRequest;
import com.bjit.tss.model.ValidationRequest;
import com.bjit.tss.service.RegisterService;
import com.bjit.tss.model.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth/register")
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping("/applicant")
    public ResponseEntity<ApiResponse<?>> applicantRegistration(@RequestBody RegisterRequest registerRequest) {
        return registerService.applicantRegistration(registerRequest);
    }

    @PostMapping("/evaluator")
    public ResponseEntity<ApiResponse<?>> evaluatorRegistration(@RequestBody EvaluatorRegisterRequest evaluatorRegisterRequest) {
        return registerService.evaluatorRegistration(evaluatorRegisterRequest);
    }

    @GetMapping("/send-email-verification")
    public ResponseEntity<ApiResponse<?>> sendEmailVerification(){
        return registerService.sendEmailVerification();
    }

    @PostMapping("/applicant/validation")
    public ResponseEntity<ApiResponse<?>> mailValidation(@RequestBody ValidationRequest validationRequest) {
        return registerService.mailValidation(validationRequest);
    }
}