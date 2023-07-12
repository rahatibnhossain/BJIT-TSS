package com.bjit.tss.service.impl;

import com.bjit.tss.entity.CourseInfo;
import com.bjit.tss.exception.CourseException;
import com.bjit.tss.mapper.CourseMapper;
import com.bjit.tss.model.ApiResponse;
import com.bjit.tss.model.CourseModel;
import com.bjit.tss.repository.CourseRepository;
import com.bjit.tss.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;


    @Override
    public ResponseEntity<ApiResponse<?>> createCourse(CourseModel courseModel) {
        Optional<CourseInfo> course = courseRepository.findByBatchCode(courseModel.getBatchCode());
        if (course.isPresent()){
            throw new CourseException("A course already exists with the batch code "+ courseModel.getBatchCode());
        }

        CourseInfo courseInfo = CourseMapper.mapToCourseInfo(courseModel);

        CourseInfo savedCourse= courseRepository.save(courseInfo);


        return new ResponseEntity<ApiResponse<?>>(ApiResponse
                .builder()
                .data(savedCourse)
                .build(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ApiResponse<?>> allCourses() {
        List<CourseInfo> courseInfoList= courseRepository.findAll();
        return new ResponseEntity<ApiResponse<?>>(ApiResponse
                .builder()
                .data(courseInfoList)
                .build(), HttpStatus.OK);
    }

}
