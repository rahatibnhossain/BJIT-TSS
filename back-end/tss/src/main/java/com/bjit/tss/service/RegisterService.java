package com.bjit.tss.service;

import com.bjit.tss.model.RegisterRequest;
import com.bjit.tss.model.ApiResponse;

public interface RegisterService {
    ApiResponse applicantRegistration(RegisterRequest registerRequest);
}
