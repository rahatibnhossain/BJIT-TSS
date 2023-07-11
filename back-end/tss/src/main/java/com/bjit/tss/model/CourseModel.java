package com.bjit.tss.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseModel {

    private String courseName;
    private Date applicationDeadline;
    private Date startDate;
    private Date endDate;
    private String batchCode;
    private String courseDescription;
}
