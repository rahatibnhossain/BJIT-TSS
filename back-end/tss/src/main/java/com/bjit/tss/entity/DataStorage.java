package com.bjit.tss.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "data_storage")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataStorage {

    @Id
    private Long dataId;

    private String dataKey;
    private String dataValue;

}
