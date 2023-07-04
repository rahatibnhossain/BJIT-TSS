package com.bjit.tss.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "course_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long courseId;
    private String courseName;
    private String applicationDeadline;
    private String startDate;
    private String endDate;




}
