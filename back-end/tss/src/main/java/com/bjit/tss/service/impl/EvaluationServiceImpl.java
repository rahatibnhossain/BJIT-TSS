package com.bjit.tss.service.impl;

import com.bjit.tss.entity.CandidateMarks;
import com.bjit.tss.entity.EvaluatorInfo;
import com.bjit.tss.entity.WrittenMarks;
import com.bjit.tss.exception.EmailException;
import com.bjit.tss.exception.UserException;
import com.bjit.tss.mapper.ApiResponseMapper;

import com.bjit.tss.model.ApiResponse;
import com.bjit.tss.model.AssignAnswerSheetRequest;
import com.bjit.tss.repository.CandidateRepository;
import com.bjit.tss.repository.EvaluatorRepository;
import com.bjit.tss.service.EvaluationService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EvaluationServiceImpl implements EvaluationService {

    private final EvaluatorRepository evaluatorRepository;
    private final CandidateRepository candidateRepository;

    @Override
    public ResponseEntity<ApiResponse<?>> assignAnswerSheet(AssignAnswerSheetRequest request) {
        Optional<EvaluatorInfo> evaluatorInfo = evaluatorRepository.findById(request.getEvaluatorId());
         if (evaluatorInfo.isEmpty()){
             throw new UserException("Evaluator does not exist");
         }
        List<CandidateMarks> candidateMarks = candidateRepository.findAllById(request.getCandidateIds());
//        WrittenMarks writtenMarks = WrittenMarks.builder()
//                .evaluatorInfo(evaluatorInfo.get())
//                .build();

        List<CandidateMarks> result =   candidateMarks.stream().map(candidate->{
            WrittenMarks writtenMarks = WrittenMarks.builder()
                    .evaluatorInfo(evaluatorInfo.get())
                    .build();
            candidate.setWrittenVivaMarks(writtenMarks);
            return candidate;
        }).toList();

        List<CandidateMarks> savedCandidates = candidateRepository.saveAll(result);
        return ApiResponseMapper.mapToResponseEntityOK(savedCandidates);
    }
}
