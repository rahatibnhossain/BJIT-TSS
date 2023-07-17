package com.bjit.tss.service;

import com.bjit.tss.model.ApiResponse;
import com.bjit.tss.model.AssignAnswerSheetRequest;
import com.bjit.tss.model.UploadMarkRequest;
import com.bjit.tss.model.UploadWrittenMarkRequest;
import org.springframework.http.ResponseEntity;

public interface EvaluationService {

    ResponseEntity<ApiResponse<?>> assignAnswerSheet(AssignAnswerSheetRequest assignAnswerSheetRequest);

    ResponseEntity<ApiResponse<?>> uploadWrittenMark(UploadWrittenMarkRequest uploadWrittenMarkRequest);

    ResponseEntity<ApiResponse<?>> uploadAptitudeMark(UploadMarkRequest uploadMarkRequest);

    ResponseEntity<ApiResponse<?>> uploadTechnicalMark(UploadMarkRequest uploadMarkRequest);

    ResponseEntity<ApiResponse<?>> uploadHrVivaMark(UploadMarkRequest uploadMarkRequest);
}