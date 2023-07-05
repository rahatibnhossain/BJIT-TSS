package com.bjit.tss.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "hr_viva_question_marks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HRVivaQuestionMarks {
    @Id
    @GeneratedValue
    @Column(name = "hr_viva_question_id")
    private Long hrVivaQuestionId;
    private Float hrVivaQuestionMark;
    private Integer questionNo;


}
