package com.bjit.tss.service.impl;


import com.bjit.tss.entity.EvaluatorInfo;
import com.bjit.tss.mapper.ApiResponseMapper;
import com.bjit.tss.model.ApiResponse;
import com.bjit.tss.model.ApplicantDashboardMessage;
import com.bjit.tss.model.ListResponse;
import com.bjit.tss.repository.EvaluatorRepository;
import com.bjit.tss.service.EvaluatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class EvaluatorServiceImpl implements EvaluatorService {
    private final EvaluatorRepository evaluatorRepository;

    @Override
    public ResponseEntity<ApiResponse<?>> getAllEvaluator() {
        List<EvaluatorInfo> evaluatorInfo = evaluatorRepository.findAll();

        if (evaluatorInfo.size() == 0 ){


            ListResponse<?> listResponse = ListResponse.builder()
                    .dataLength(0)
                    .listResponse(evaluatorInfo)
                    .build();
            return ApiResponseMapper.mapToResponseEntityOK(listResponse, "Evaluator List is empty");
        }
        else {

            ListResponse<?> listResponse = ListResponse.builder()
                    .dataLength(evaluatorInfo.size())
                    .listResponse(evaluatorInfo)
                    .build();
            return ApiResponseMapper.mapToResponseEntityOK(listResponse, "Evaluator List");

        }

    }
}