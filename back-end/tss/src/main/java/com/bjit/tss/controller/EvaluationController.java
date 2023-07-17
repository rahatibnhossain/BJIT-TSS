package com.bjit.tss.controller;

import com.bjit.tss.model.ApiResponse;
import com.bjit.tss.model.AssignAnswerSheetRequest;
import com.bjit.tss.model.UploadMarkRequest;
import com.bjit.tss.model.UploadWrittenMarkRequest;
import com.bjit.tss.service.EvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/evaluation")
@RequiredArgsConstructor
public class EvaluationController {

    private final EvaluationService evaluationService;

    @PostMapping("/assign-answer")
    public ResponseEntity<ApiResponse<?>> assignAnswerSheet(@RequestBody AssignAnswerSheetRequest assignAnswerSheetRequest) {
        return evaluationService.assignAnswerSheet(assignAnswerSheetRequest);
    }

    @PostMapping("/upload-mark/written")
    public ResponseEntity<ApiResponse<?>> uploadWrittenMark(@RequestBody UploadWrittenMarkRequest uploadWrittenMarkRequest) {
        return evaluationService.uploadWrittenMark(uploadWrittenMarkRequest);
    }

    @PostMapping("/upload-mark/aptitude")
    public ResponseEntity<ApiResponse<?>> uploadAptitudeMark(@RequestBody UploadMarkRequest UploadMarkRequest) {
        return evaluationService.uploadAptitudeMark(UploadMarkRequest);
    }

    @PostMapping("upload-mark/technical")
    public ResponseEntity<ApiResponse<?>> uploadTechnicalMark(@RequestBody UploadMarkRequest UploadMarkRequest) {
        return evaluationService.uploadTechnicalMark(UploadMarkRequest);
    }

    @PostMapping("/upload-mark/hr-viva")
    public ResponseEntity<ApiResponse<?>> uploadHrVivaMark(@RequestBody UploadMarkRequest UploadMarkRequest) {
        return evaluationService.uploadHrVivaMark(UploadMarkRequest);
    }
}