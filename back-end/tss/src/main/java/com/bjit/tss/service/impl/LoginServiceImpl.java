package com.bjit.tss.service.impl;

import com.bjit.tss.config.JwtService;
import com.bjit.tss.entity.LoginInfo;
import com.bjit.tss.entity.UserInfo;
import com.bjit.tss.enums.Role;
import com.bjit.tss.exception.AuthenticationException;
import com.bjit.tss.exception.UserException;
import com.bjit.tss.mapper.ApiResponseMapper;
import com.bjit.tss.model.AuthenticationResponse;
import com.bjit.tss.model.LoginRequest;
import com.bjit.tss.model.ValidationResponse;
import com.bjit.tss.repository.LoginRepository;
import com.bjit.tss.repository.UserRepository;
import com.bjit.tss.service.LoginService;
import com.bjit.tss.model.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final AuthenticationManager authenticationManager;
    private final LoginRepository loginRepository;
    private final JwtService jwtService;
    private final UserRepository userRepository;

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
                    .role(loginInfo.getRole())
                    .build();
            case EVALUATOR -> authenticationResponse = AuthenticationResponse.builder()
                    .token(jwtToken)
                    .role(loginInfo.getRole())
                    .evaluatorInfo(loginInfo.getEvaluatorInfo())
                    .build();
            default -> authenticationResponse = AuthenticationResponse.builder()
                    .token(jwtToken)
                    .role(loginInfo.getRole())
                    .userInfo(loginInfo.getUserInfo())
                    .build();
        }

        return ApiResponseMapper.mapToResponseEntityOK(authenticationResponse,"Login successfully done.");
    }

    @Override
    public ResponseEntity<ApiResponse<?>> validation() {

        LoginInfo loginInfo= (LoginInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (loginInfo.getRole()== Role.APPLICANT){
            Optional<UserInfo> userInfo = userRepository.findById(loginInfo.getUserInfo().getUserId());
            if (userInfo.isEmpty()){
                throw new UserException("User not exists");
            }
            ValidationResponse<?> validationResponse = ValidationResponse.builder()
                    .data(userInfo)
                    .role(loginInfo.getRole())
                    .build();
            return ApiResponseMapper.mapToResponseEntityOK(validationResponse, "Valid User");
        }
        if (loginInfo.getRole()== Role.USER){
            ValidationResponse<?> validationResponse = ValidationResponse.builder()
                    .data(loginInfo.getEmail())
                    .role(loginInfo.getRole())
                    .build();
            return ApiResponseMapper.mapToResponseEntityOK(validationResponse, "Valid User");

        }


        return ApiResponseMapper.mapToResponseEntityOK(null, "Valid User");
    }
}