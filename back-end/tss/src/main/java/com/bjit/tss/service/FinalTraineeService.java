package com.bjit.tss.service;

import com.bjit.tss.model.ApiResponse;
import com.bjit.tss.model.FinalTraineeSelectionRequest;
import org.springframework.http.ResponseEntity;

public interface FinalTraineeService {
    
    ResponseEntity<ApiResponse<?>> allPassedFinalTrainee(String batchCode);

    ResponseEntity<ApiResponse<?>> selectFinalTrainee(FinalTraineeSelectionRequest request);
}