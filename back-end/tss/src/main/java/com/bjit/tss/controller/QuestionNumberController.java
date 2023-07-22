package com.bjit.tss.controller;

import com.bjit.tss.model.ApiResponse;
import com.bjit.tss.model.QuestionNumberRequest;
import com.bjit.tss.service.QuestionNumberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/number-question")
@RequiredArgsConstructor
public class QuestionNumberController {

    private final QuestionNumberService questionNumberService;

    @PostMapping("/set")
    public ResponseEntity<ApiResponse<?>> setWrittenQuestionNumber(@Valid @RequestBody QuestionNumberRequest questionNumberRequest) {
        return questionNumberService.setWrittenQuestionNumber(questionNumberRequest);
    }
}