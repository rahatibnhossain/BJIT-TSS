package com.bjit.tss.service.impl;

import com.bjit.tss.config.JwtService;
import com.bjit.tss.entity.LoginInfo;
import com.bjit.tss.entity.ValidationCodes;
import com.bjit.tss.exception.EmailException;
import com.bjit.tss.exception.ValidationException;
import com.bjit.tss.mapper.ApiResponseMapper;
import com.bjit.tss.model.*;
import com.bjit.tss.repository.ValidationRepository;
import com.bjit.tss.role.Role;
import com.bjit.tss.entity.UserInfo;
import com.bjit.tss.exception.AuthenticationException;
import com.bjit.tss.repository.LoginRepository;
import com.bjit.tss.service.EmailService;
import com.bjit.tss.service.RegisterService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final ValidationRepository validationRepository;
    private final EmailService emailService;

    @Transactional
    @Override
    public ResponseEntity<ApiResponse<?>> applicantRegistration(RegisterRequest registerRequest) {

        if (registerRequest.getEmail()== null || registerRequest.getEmail().isEmpty()){
            throw new AuthenticationException("Email is required.");

        }
        String emailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(emailRegex);

        if (!pattern.matcher(registerRequest.getEmail()).matches()){
            throw new EmailException("Invalid Email");

        }

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
                .build();

        LoginInfo loginInfo = LoginInfo.builder()
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .userInfo(userInfo)
                .build();

        ValidationCodes validationCodes = ValidationCodes.builder()
                .userInfo(userInfo)
                .build();



        List<String> to = new ArrayList<String>();
        to.add(loginInfo.getEmail());
        String[] toEmail = to.toArray(new String[0]);

        String emailSubject= "Email Verification";
        String emailBody = "Your Verification Code is : "+validationCodes.getValidationCode();


        EmailRequest emailRequest = EmailRequest.builder()
                .to(toEmail)
                .body(emailBody)
                .subject(emailSubject)
                .build();
        ResponseEntity<ApiResponse<?>> emailResponse=  emailService.sendEmail(emailRequest);



         validationRepository.save(validationCodes);


        LoginInfo saved=  loginRepository.save(loginInfo);
        String jwtToken = jwtService.generateToken(loginInfo);
        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                .token(jwtToken)
                .userInfo(saved.getUserInfo())
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

    @Override
    public ResponseEntity<ApiResponse<?>> mailValidation(ValidationRequest validationRequest) {
        LoginInfo loginInfo = (LoginInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<ValidationCodes> validationCodes = validationRepository.findByUserInfoUserId(loginInfo.getUserInfo().getUserId());

        if (validationCodes.isEmpty()){
            throw new ValidationException("Cannot be validated");
        }
        if (validationRequest.getValidationCode().equals(validationCodes.get().getValidationCode())){
            loginInfo.setRole(Role.APPLICANT);
            loginRepository.save(loginInfo);
        }
        else {
            throw new ValidationException("Invalid Validation Code");
        }

        SuccessMessageResponse successMessageResponse = SuccessMessageResponse.builder()
                .successMessage("Email is validated")
                .build();

        return ApiResponseMapper.mapToResponseEntityOK(successMessageResponse);
    }
}
