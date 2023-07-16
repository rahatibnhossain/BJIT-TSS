package com.bjit.tss.controller;

import com.bjit.tss.model.ApiResponse;
import com.bjit.tss.model.CourseModel;
import com.bjit.tss.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getCourses() {
        return courseService.allCourses();
    }

    @GetMapping("/batch-code/{batchCode}")
    public ResponseEntity<ApiResponse<?>> getCourse(@PathVariable String batchCode) {
        return courseService.getCourse(batchCode);
    }

    @PostMapping("/update/batch_code/{batchCode}")
    public ResponseEntity<ApiResponse<?>> updateCourse(@PathVariable String batchCode, @RequestBody CourseModel courseModel) {
        return courseService.updateCourse(batchCode, courseModel);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<?>> createCourse(@RequestBody CourseModel courseModel) {
        return courseService.createCourse(courseModel);
    }
}
