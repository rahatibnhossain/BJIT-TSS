package com.bjit.tss.service.impl;

import com.bjit.tss.entity.CourseInfo;
import com.bjit.tss.entity.ExamineeInfo;
import com.bjit.tss.entity.LoginInfo;
import com.bjit.tss.entity.UserInfo;
import com.bjit.tss.exception.CourseException;
import com.bjit.tss.exception.ExamineeException;
import com.bjit.tss.exception.UserException;
import com.bjit.tss.mapper.ApiResponseMapper;
import com.bjit.tss.model.ApiResponse;
import com.bjit.tss.model.ApplicationRequest;
import com.bjit.tss.model.CourseRoleRequest;
import com.bjit.tss.repository.CourseRepository;
import com.bjit.tss.repository.ExamineeRepository;
import com.bjit.tss.repository.UserRepository;
import com.bjit.tss.role.Role;
import com.bjit.tss.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final ExamineeRepository examineeRepository;


    @Override
    public ResponseEntity<ApiResponse<?>> applyCourse(ApplicationRequest applicationRequest) {



        Optional<UserInfo> userInfo = userRepository.findByEmail(applicationRequest.getEmail());

        if (userInfo.isEmpty()) {
            throw new UserException("Invalid user : " + applicationRequest.getEmail());
        }

        LoginInfo loginInfo = (LoginInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!userInfo.get().getVerified()){
            throw new UserException("Your Email : " + applicationRequest.getEmail() + " is not verified ");

        }
        if(!loginInfo.getEmail().equals(userInfo.get().getEmail())){
            throw new UserException("Invalid Token!!!");

        }




        Optional<CourseInfo> courseInfo = courseRepository.findByBatchCode(applicationRequest.getBatchCode());
        if (courseInfo.isEmpty()) {
            throw new CourseException("Invalid Batch Code : " + applicationRequest.getBatchCode());
        }
        Optional<ExamineeInfo> examinee = examineeRepository.findByUserInfoUserIdAndCourseInfoCourseId(userInfo.get().getUserId(), courseInfo.get().getCourseId());
        if (examinee.isPresent()) {
            throw new ExamineeException("You are already registered for this course");

        }


        ExamineeInfo examineeInfo = ExamineeInfo.builder()
                .userInfo(userInfo.get())
                .courseInfo(courseInfo.get())
                .applicationTime(new Date(System.currentTimeMillis()))
                .role(Role.APPLICANT)
                .build();

        ExamineeInfo savedApplication = examineeRepository.save(examineeInfo);
        return ApiResponseMapper.mapToResponseEntityCreated(savedApplication);
    }




    @Override
    public ResponseEntity<ApiResponse<?>> allApplicationSpecific(CourseRoleRequest courseRoleRequest) {
        Optional<List<ExamineeInfo>> examineeInfos = examineeRepository.findByRoleAndCourseInfoIsAvailableAndCourseInfoBatchCode(courseRoleRequest.getRole(), true, courseRoleRequest.getBatchCode());
        return ApiResponseMapper.mapToResponseEntityOK(examineeInfos);
    }
}
