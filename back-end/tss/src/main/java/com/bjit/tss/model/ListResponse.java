package com.bjit.tss.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListResponse<T> {
    private Long  dataLength ;
    private T listResponse;
}
