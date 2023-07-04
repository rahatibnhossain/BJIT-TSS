package com.bjit.tss.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hr_viva_marks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HRVivaMarks {

    @Id
    @GeneratedValue
    @Column(name = "hr_viva_id")
    private Long hrVivaId;
    private Float hrVivaMark;
    private Boolean passed;





    @OneToMany(targetEntity = HRVivaQuestionMarks.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "hr_viva_id", referencedColumnName = "hr_viva_id")
    private List<HRVivaQuestionMarks> hrQuestionMarks;

}
