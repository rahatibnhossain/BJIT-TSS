package com.bjit.tss.service;

import com.bjit.tss.model.ApiResponse;
import com.bjit.tss.model.ApprovalRequest;
import org.springframework.http.ResponseEntity;

public interface ApprovalService {

    ResponseEntity<ApiResponse<?>> markAs(ApprovalRequest approvalRequest);
}
