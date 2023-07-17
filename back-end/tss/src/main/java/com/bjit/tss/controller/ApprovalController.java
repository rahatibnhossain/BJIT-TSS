package com.bjit.tss.controller;

import com.bjit.tss.model.ApiResponse;
import com.bjit.tss.model.ApprovalRequest;
import com.bjit.tss.service.ApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/approval")
public class ApprovalController {

    private final ApprovalService approvalService;

    @PostMapping("/applicant")
    public ResponseEntity<ApiResponse<?>> markAs(@RequestBody ApprovalRequest approvalRequest) {
        return approvalService.approveApplicant(approvalRequest);
    }
}