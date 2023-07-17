package com.bjit.tss.model;

import com.bjit.tss.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseRoleRequest {

    private Role role;
    private String batchCode;
}
