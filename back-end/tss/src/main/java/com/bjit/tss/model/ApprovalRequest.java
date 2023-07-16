package com.bjit.tss.model;

import com.bjit.tss.role.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApprovalRequest {

    private Long examineeId;
    private Role role;
}