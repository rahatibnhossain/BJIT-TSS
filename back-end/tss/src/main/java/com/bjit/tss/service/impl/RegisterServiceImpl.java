package com.bjit.tss.service.impl;

import com.bjit.tss.config.JwtService;
import com.bjit.tss.entity.LoginInfo;
import com.bjit.tss.entity.Role;
import com.bjit.tss.entity.UserInfo;
import com.bjit.tss.model.AuthenticationResponse;
import com.bjit.tss.model.RegisterRequest;
import com.bjit.tss.repository.LoginRepository;
import com.bjit.tss.service.RegisterService;
import com.bjit.tss.model.ApiResponse;
import lombok.RequiredArgsConstructor;
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
    public ApiResponse applicantRegistration(RegisterRequest registerRequest) {
        Optional<LoginInfo> checkAvailability = loginRepository.findByEmail(registerRequest.getEmail());

        if (checkAvailability.isPresent()){
            return new ApiResponse().builder().data("Not Available").build();
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
        var jwtToken = jwtService.generateToken(loginInfo);
        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
        return new ApiResponse().builder().data(authenticationResponse).build();
    }
}
