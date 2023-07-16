package com.bjit.tss.service;

import com.bjit.tss.model.ApiResponse;
import com.bjit.tss.model.QuestionNumberRequest;
import org.springframework.http.ResponseEntity;

public interface QuestionNumberService {

    ResponseEntity<ApiResponse<?>> setWrittenQuestionNumber(QuestionNumberRequest questionNumberRequest);
}