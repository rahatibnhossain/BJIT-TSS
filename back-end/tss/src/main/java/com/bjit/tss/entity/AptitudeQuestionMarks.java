package com.bjit.tss.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "aptitude_question_marks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AptitudeQuestionMarks {
    @Id
    @GeneratedValue
    private Long aptitudeQuestionId;
    private Float aptitudeQuestionMark;
    private Integer questionNo;


}
