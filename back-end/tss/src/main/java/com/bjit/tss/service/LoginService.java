package com.bjit.tss.service;

import com.bjit.tss.model.LoginRequest;
import com.bjit.tss.model.ApiResponse;

public interface LoginService {
   ApiResponse login(LoginRequest loginRequest);
}
