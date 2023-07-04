package com.bjit.tss.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "aptitude_test_marks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AptitudeTestMarks {

    @Id
    @GeneratedValue
    @Column(name = "aptitude_test_id")
    private Long aptitudeTestId;
    private Float aptitudeTestMark;
    private Boolean passed;

    @OneToMany(targetEntity = AptitudeQuestionMarks.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "aptitude_test_id", referencedColumnName = "aptitude_test_id")
    private List<AptitudeQuestionMarks> aptitudeQuestionMarks;

}
