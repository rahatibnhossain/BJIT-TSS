package com.bjit.tss.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "course_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseInfo {
    @Id
    @GeneratedValue
    @Column(name = "course_id")
    private Long courseId;
    private String courseName;
    private Date applicationDeadline;
    private Date startDate;
    private Date endDate;
    private String batchCode;
    private String courseDescription;

}
