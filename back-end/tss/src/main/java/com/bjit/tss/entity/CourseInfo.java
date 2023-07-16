package com.bjit.tss.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "course_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long courseId;
    private String courseName;
    private Date applicationDeadline;
    private Date writtenExamTime;
    private Date startDate;
    private Date endDate;
    private String batchCode;
    private String courseDescription;
    private Boolean isAvailable;
}