package com.bjit.tss.service.impl;

import com.bjit.tss.exception.EmailException;
import com.bjit.tss.mapper.ApiResponseMapper;

import com.bjit.tss.model.ApiResponse;
import com.bjit.tss.model.AssignAnswerSheetRequest;
import com.bjit.tss.service.EvaluationService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EvaluationServiceImpl implements EvaluationService {


    @Override
    public ResponseEntity<ApiResponse<?>> assignAnswerSheet(AssignAnswerSheetRequest assignAnswerSheetRequest) {


        return null;
    }
}
