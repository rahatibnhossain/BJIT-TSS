package com.bjit.tss.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "question_marks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionMarks {
    @Id
    @GeneratedValue
    @Column(name = "question_id")
    private Long writtenQuestionId;
    private Float writtenQuestionMark;
    private Integer questionNo;


}
