package com.bjit.tss.controller;

import com.bjit.tss.model.ApiResponse;
import com.bjit.tss.model.ApplicationRequest;
import com.bjit.tss.model.AssignAnswerSheetRequest;
import com.bjit.tss.model.CourseRoleRequest;
import com.bjit.tss.service.ApplicationService;
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
    public ResponseEntity<ApiResponse<?>> assignAnswerSheet(@RequestBody AssignAnswerSheetRequest assignAnswerSheetRequest){
        return evaluationService.assignAnswerSheet(assignAnswerSheetRequest);
    }



}
