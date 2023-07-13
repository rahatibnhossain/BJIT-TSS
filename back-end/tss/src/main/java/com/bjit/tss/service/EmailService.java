package com.bjit.tss.service;

import com.bjit.tss.model.ApiResponse;
import com.bjit.tss.model.EmailRequest;
import org.springframework.http.ResponseEntity;

public interface EmailService {
    ResponseEntity<ApiResponse<?>> sendEmail(EmailRequest emailRequest);
}
