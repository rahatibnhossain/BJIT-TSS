package com.bjit.tss.service;


import com.bjit.tss.model.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface EvaluatorService {
    ResponseEntity<ApiResponse<?>> getAllEvaluator();

    ResponseEntity<ApiResponse<?>> getAssignedCandidate(Long evaluatorId);
}
