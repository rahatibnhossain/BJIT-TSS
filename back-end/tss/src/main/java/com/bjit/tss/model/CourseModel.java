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
    private Boolean isAvailable;
    private Long vacancy;
    private Date writtenExamTime;
    private String applicantDashboardMessage;
    private String writtenShortlistedDashboardMessage;
    private String writtenPassedDashboardMessage;
    private String aptitudeTestPassedDashboardMessage;
    private String technicalVivaPassedDashboardMessage;
    private String hrVivaPassedDashboardMessage;
    private String traineeDashboardMessage;
}