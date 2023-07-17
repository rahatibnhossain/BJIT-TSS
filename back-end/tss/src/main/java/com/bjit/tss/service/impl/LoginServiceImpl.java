package com.bjit.tss.service.impl;

import com.bjit.tss.config.JwtService;
import com.bjit.tss.entity.LoginInfo;
import com.bjit.tss.exception.AuthenticationException;
import com.bjit.tss.mapper.ApiResponseMapper;
import com.bjit.tss.model.AuthenticationResponse;
import com.bjit.tss.model.LoginRequest;
import com.bjit.tss.repository.LoginRepository;
import com.bjit.tss.service.LoginService;
import com.bjit.tss.model.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final AuthenticationManager authenticationManager;
    private final LoginRepository loginRepository;
    private final JwtService jwtService;

    @Override
    public ResponseEntity<ApiResponse<?>> login(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    ));
        } catch (Exception ex) {
            throw new AuthenticationException("Invalid Email and Password");
        }

        LoginInfo loginInfo = loginRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow();
        String jwtToken = jwtService.generateToken(loginInfo);
        AuthenticationResponse authenticationResponse = null;
        switch (loginInfo.getRole()) {
            case ADMIN -> authenticationResponse = AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
            case EVALUATOR -> authenticationResponse = AuthenticationResponse.builder()
                    .token(jwtToken)
                    .evaluatorInfo(loginInfo.getEvaluatorInfo())
                    .build();
            default -> authenticationResponse = AuthenticationResponse.builder()
                    .token(jwtToken)
                    .userInfo(loginInfo.getUserInfo())
                    .build();
        }

        return ApiResponseMapper.mapToResponseEntityOK(authenticationResponse,"Login successfully done.");
    }
}