package com.bjit.tss.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "written_question_marks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WrittenQuestionMarks {
    @Id
    @GeneratedValue
    @Column(name = "written_question_id")
    private Long writtenQuestionId;
    private Float writtenQuestionMark;
    private Integer questionNo;


}
