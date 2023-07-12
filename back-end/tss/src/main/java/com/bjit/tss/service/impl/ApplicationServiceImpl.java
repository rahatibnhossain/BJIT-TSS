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
import com.bjit.tss.repository.CourseRepository;
import com.bjit.tss.repository.ExamineeRepository;
import com.bjit.tss.repository.UserRepository;
import com.bjit.tss.role.Role;
import com.bjit.tss.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final ExamineeRepository examineeRepository;


    @Override
    public ResponseEntity<ApiResponse<?>> applyCourse(ApplicationRequest applicationRequest) {

        Optional<CourseInfo> courseInfo = courseRepository.findByBatchCode(applicationRequest.getBatchCode());
        if (courseInfo.isEmpty()) {
            throw new CourseException("Invalid Batch Code : " + applicationRequest.getBatchCode());
        }

        Optional<UserInfo> userInfo = userRepository.findByEmail(applicationRequest.getEmail());

        if (userInfo.isEmpty()) {
            throw new UserException("Invalid user : " + applicationRequest.getEmail());
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
}
