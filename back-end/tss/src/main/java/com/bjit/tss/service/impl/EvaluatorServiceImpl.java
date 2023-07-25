package com.bjit.tss.service.impl;


import com.bjit.tss.entity.CandidateMarks;
import com.bjit.tss.entity.EvaluatorInfo;
import com.bjit.tss.mapper.ApiResponseMapper;
import com.bjit.tss.mapper.CandidateMapper;
import com.bjit.tss.model.response.ApiResponse;
import com.bjit.tss.model.response.ListResponse;
import com.bjit.tss.model.response.CandidateResponse;
import com.bjit.tss.repository.CandidateRepository;
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
    private final CandidateRepository candidateRepository;

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

    @Override
    public ResponseEntity<ApiResponse<?>> getAssignedCandidate(Long evaluatorId) {
        List<CandidateMarks> candidateMarks = candidateRepository.findByWrittenMarksEvaluatorInfoEvaluatorIdAndExamineeInfoCourseInfoIsAvailable(evaluatorId, true);

       List<CandidateResponse> candidateResponseList = candidateMarks.stream().map(CandidateMapper::mapToCandidateResponse).toList();

        ListResponse listResponse = ListResponse.builder()
                .dataLength(candidateResponseList.size())
                .listResponse(candidateResponseList)
                .build();



        return ApiResponseMapper.mapToResponseEntityOK(listResponse, "List of assigned candidates to evaluator");
    }


}