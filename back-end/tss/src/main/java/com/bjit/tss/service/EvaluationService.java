package com.bjit.tss.service;

import com.bjit.tss.model.ApiResponse;
import com.bjit.tss.model.AssignAnswerSheetRequest;
import org.springframework.http.ResponseEntity;

public interface EvaluationService {
    ResponseEntity<ApiResponse<?>> assignAnswerSheet(AssignAnswerSheetRequest assignAnswerSheetRequest);
}
