package com.bjit.tss.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "candidate_marks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidateMarks {

    @Id
    @GeneratedValue
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "examinee_id")
    private ExamineeInfo examineeInfo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "technical_viva_id")
    private TechnicalVivaMarks technicalVivaMarks;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "aptitude_test_id")
    private AptitudeTestMarks aptitudeTestMarks;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hr_viva_id")
    private HRVivaMarks hrVivaMarks;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "written_mark_id")
    private WrittenVivaMarks writtenVivaMarks;

    private Float fullMark;
}
