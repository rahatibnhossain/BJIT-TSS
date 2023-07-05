package com.bjit.tss.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "aptitude_question_marks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AptitudeQuestionMarks {
    @Id
    @GeneratedValue
    @Column(name = "aptitude_question_id")
    private Long aptitudeQuestionId;
    private Float aptitudeQuestionMark;
    private Integer questionNo;


}
