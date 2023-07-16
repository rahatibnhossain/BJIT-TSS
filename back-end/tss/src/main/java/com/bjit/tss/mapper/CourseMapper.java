package com.bjit.tss.mapper;

import com.bjit.tss.entity.CourseInfo;
import com.bjit.tss.model.CourseModel;

public class CourseMapper {

    public static CourseInfo mapToCourseInfo(CourseModel courseModel) {
        CourseInfo courseInfo = CourseInfo.builder()
                .courseName(courseModel.getCourseName())
                .applicationDeadline(courseModel.getApplicationDeadline())
                .startDate(courseModel.getStartDate())
                .endDate(courseModel.getEndDate())
                .batchCode(courseModel.getBatchCode())
                .courseDescription(courseModel.getCourseDescription())
                .isAvailable(courseModel.getIsAvailable())
                .writtenExamTime(courseModel.getWrittenExamTime())
                .build();
        return courseInfo;
    }
}