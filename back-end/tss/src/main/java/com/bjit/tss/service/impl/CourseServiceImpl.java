package com.bjit.tss.service.impl;

import com.bjit.tss.entity.CourseInfo;
import com.bjit.tss.exception.CourseException;
import com.bjit.tss.mapper.ApiResponseMapper;
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
import java.util.Objects;
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

        return ApiResponseMapper.mapToResponseEntityCreated(savedCourse);

    }

    @Override
    public ResponseEntity<ApiResponse<?>> allCourses() {
        List<CourseInfo> courseInfoList= courseRepository.findAll();

        return ApiResponseMapper.mapToResponseEntityOK(courseInfoList);

    }

    @Override
    public ResponseEntity<ApiResponse<?>> getCourse(String batchCode) {

        Optional<CourseInfo> courseInfo = courseRepository.findByBatchCode(batchCode);
        if (courseInfo.isEmpty()){
            throw new CourseException("Invalid Batch Code : "+batchCode);
        }
        return ApiResponseMapper.mapToResponseEntityOK(courseInfo);
    }

    @Override
    public ResponseEntity<ApiResponse<?>> updateCourse(String batchCode, CourseModel courseModel) {
        Optional<CourseInfo> course = courseRepository.findByBatchCode(batchCode);
        if (course.isEmpty()){
            throw new CourseException("Invalid Batch Code : "+batchCode);
        }
        Optional<CourseInfo> courseModelCode = courseRepository.findByBatchCode(courseModel.getBatchCode());
        if (courseModelCode.isPresent() && !Objects.equals(batchCode.toUpperCase() , courseModel.getBatchCode().toUpperCase())){
            
            throw new CourseException("This batch code : "+courseModel.getBatchCode()+ " you want to update to already exists");
        }

        CourseInfo courseInfo = CourseMapper.mapToCourseInfo(courseModel);
        courseInfo.setCourseId(course.get().getCourseId());
        CourseInfo savedCourse= courseRepository.save(courseInfo);
        return ApiResponseMapper.mapToResponseEntityOK(savedCourse);
    }


}
