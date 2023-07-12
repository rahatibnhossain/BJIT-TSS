package com.bjit.tss.service.impl;

import com.bjit.tss.config.JwtService;
import com.bjit.tss.entity.LoginInfo;
import com.bjit.tss.mapper.ApiResponseMapper;
import com.bjit.tss.role.Role;
import com.bjit.tss.entity.UserInfo;
import com.bjit.tss.exception.AuthenticationException;
import com.bjit.tss.model.AuthenticationResponse;
import com.bjit.tss.model.RegisterRequest;
import com.bjit.tss.repository.LoginRepository;
import com.bjit.tss.service.RegisterService;
import com.bjit.tss.model.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public ResponseEntity<ApiResponse<?>> applicantRegistration(RegisterRequest registerRequest) {
        Optional<LoginInfo> checkAvailability = loginRepository.findByEmail(registerRequest.getEmail());

        if (checkAvailability.isPresent()) {
            throw new AuthenticationException("The Email " + registerRequest.getEmail() + " is already registered");
        }

        UserInfo userInfo = UserInfo.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .gender(registerRequest.getGender())
                .dateOfBirth(registerRequest.getDateOfBirth())
                .email(registerRequest.getEmail())
                .contactNumber(registerRequest.getContactNumber())
                .degreeName(registerRequest.getDegreeName())
                .educationalInstitute(registerRequest.getEducationalInstitute())
                .cgpa(registerRequest.getCgpa())
                .passingYear(registerRequest.getPassingYear())
                .presentAddress(registerRequest.getPresentAddress())
                .photoUrl(registerRequest.getPhotoUrl())
                .resumeUrl(registerRequest.getResumeUrl())
                .verified(false)
                .build();

        LoginInfo loginInfo = LoginInfo.builder()
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.APPLICANT)
                .userInfo(userInfo)
                .build();

        loginRepository.save(loginInfo);
        String jwtToken = jwtService.generateToken(loginInfo);
        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

        return ApiResponseMapper.mapToResponseEntityCreated(authenticationResponse);

    }

    @Override
    public void adminRegistration(String email, String password) {


        Optional<LoginInfo> checkAvailability = loginRepository.findByEmail(email);

        if (checkAvailability.isEmpty()) {
            LoginInfo loginInfo = LoginInfo.builder()
                    .email(email)
                    .password(passwordEncoder.encode(password))
                    .role(Role.ADMIN)
                    .build();
            loginRepository.save(loginInfo);

        }


        return;
    }
}
