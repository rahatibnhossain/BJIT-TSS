package com.bjit.tss.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "technical_viva_marks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TechnicalVivaMarks {

    @Id
    @GeneratedValue
    @Column(name = "technical_viva_id")
    private Long technicalVivaId;
    private Float technicalVivaMark;
    private Boolean passed;





    @OneToMany(targetEntity = TechnicalQuestionMarks.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "technical_viva_id", referencedColumnName = "technical_viva_id")
    private List<TechnicalQuestionMarks> technicalQuestionMarks;

}
