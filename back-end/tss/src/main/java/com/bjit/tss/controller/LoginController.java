package com.bjit.tss.controller;

import com.bjit.tss.model.LoginRequest;
import com.bjit.tss.service.LoginService;
import com.bjit.tss.model.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest loginRequest){

        return ResponseEntity.ok(loginService.login(loginRequest));

    }
 

}
