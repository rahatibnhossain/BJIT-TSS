package com.bjit.tss.controller;

import com.bjit.tss.model.ApiResponse;
import com.bjit.tss.model.CourseModel;
import com.bjit.tss.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/course/")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;



    @PostMapping("/create")
    public ResponseEntity<ApiResponse<?>> createCourse(@RequestBody CourseModel courseModel){
        return  courseService.createCourse(courseModel);

    }
}
