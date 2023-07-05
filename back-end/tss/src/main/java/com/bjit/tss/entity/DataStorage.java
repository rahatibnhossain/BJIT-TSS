package com.bjit.tss.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "data_storage")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataStorage {

    @Id
    @GeneratedValue
    @Column(name = "data_id")
    private Long dataId;
    private String dataKey;
    private String dataValue;

}
