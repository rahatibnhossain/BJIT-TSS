package com.bjit.tss.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "hidden_code_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HiddenCodeInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hidden_code")
    private Long hiddenCode;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "examinee_id")
    private ExamineeInfo examineeInfo;
}
