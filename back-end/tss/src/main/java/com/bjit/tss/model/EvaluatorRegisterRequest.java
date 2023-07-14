package com.bjit.tss.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EvaluatorRegisterRequest {
    private String email;
    private String name;
    private String password;

}
