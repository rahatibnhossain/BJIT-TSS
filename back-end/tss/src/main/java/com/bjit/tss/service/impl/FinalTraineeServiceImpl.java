package com.bjit.tss.service.impl;

import com.bjit.tss.entity.CandidateMarks;
import com.bjit.tss.mapper.ApiResponseMapper;
import com.bjit.tss.model.ApiResponse;
import com.bjit.tss.model.ListResponse;
import com.bjit.tss.repository.CandidateRepository;
import com.bjit.tss.service.FinalTraineeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class FinalTraineeServiceImpl implements FinalTraineeService {

    private final CandidateRepository candidateRepository;
    @Override
    public ResponseEntity<ApiResponse<?>> allPassedFinalTrainee(String batchCode) {
        List<CandidateMarks> candidateMarks = candidateRepository.findAllByHrVivaPassedAndTechnicalVivaPassedAndAptitudeTestPassedAndWrittenMarksPassedAndExamineeInfoCourseInfoBatchCode(true,true,true,true,batchCode);
        ListResponse listResponse = ListResponse.builder()
                .dataLength(candidateMarks.size())
                .listResponse(candidateMarks)
                .build();
        return ApiResponseMapper.mapToResponseEntityOK(listResponse,"List of candidates who passed all the exam for this batch : "+batchCode);
    }
}