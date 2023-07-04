package com.bjit.tss.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "technical_question_marks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TechnicalQuestionMarks {
    @Id
    @GeneratedValue
    private Long technicalQuestionId;
    private Float technicalQuestionMark;
    private Integer questionNo;

}
