package com.bjit.tss.model;

import com.bjit.tss.entity.EvaluatorInfo;
import com.bjit.tss.entity.UserInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthenticationResponse {
    private String token;
    private UserInfo userInfo;
    private EvaluatorInfo evaluatorInfo;
}
