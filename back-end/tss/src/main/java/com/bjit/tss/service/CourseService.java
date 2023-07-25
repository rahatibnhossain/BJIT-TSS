package com.bjit.tss.service;

import com.bjit.tss.model.response.ApiResponse;
import com.bjit.tss.model.CourseModel;
import org.springframework.http.ResponseEntity;

public interface CourseService {

    ResponseEntity<ApiResponse<?>> createCourse(CourseModel courseModel);

    ResponseEntity<ApiResponse<?>> allCourses();

    ResponseEntity<ApiResponse<?>> getCourse(String batchCode);

    ResponseEntity<ApiResponse<?>> updateCourse(String batchCode, CourseModel courseModel);

    ResponseEntity<ApiResponse<?>> getUnavailableCourses();
}