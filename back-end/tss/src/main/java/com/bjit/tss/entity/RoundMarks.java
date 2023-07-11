package com.bjit.tss.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "round_marks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoundMarks {

    @Id
    @GeneratedValue
    @Column(name = "round_id")
    private Long roundId;
    private String roundName;
    private Float roundMark;
    private Boolean passed;

    @OneToMany(targetEntity = QuestionMarks.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "round_id", referencedColumnName = "round_id")
    private List<QuestionMarks> questionMarksList;

}