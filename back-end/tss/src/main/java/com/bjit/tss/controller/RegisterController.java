package com.bjit.tss.controller;

import com.bjit.tss.model.RegisterRequest;
import com.bjit.tss.service.RegisterService;
import com.bjit.tss.model.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping("api/auth/register")
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping("/applicant")
    public ResponseEntity<ApiResponse<?>> applicantRegistration(@RequestBody RegisterRequest registerRequest){
        return registerService.applicantRegistration(registerRequest);
    }
}
