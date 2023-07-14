package com.bjit.tss.service.impl;

import com.bjit.tss.entity.CandidateMarks;
import com.bjit.tss.entity.ExamineeInfo;
import com.bjit.tss.exception.UserException;
import com.bjit.tss.mapper.ApiResponseMapper;
import com.bjit.tss.model.ApiResponse;
import com.bjit.tss.model.ApprovalRequest;
import com.bjit.tss.repository.CandidateRepository;
import com.bjit.tss.repository.ExamineeRepository;
import com.bjit.tss.service.ApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApprovalServiceImpl implements ApprovalService {

    private final ExamineeRepository examineeRepository;

    private final CandidateRepository candidateRepository;

    @Override
    public ResponseEntity<ApiResponse<?>> markAs(ApprovalRequest request) {

        Optional<ExamineeInfo> examineeInfo = examineeRepository.findById(request.getExamineeId());
        if (examineeInfo.isEmpty()){
            throw new UserException("User not found!!!");
        }
        examineeInfo.get().setRole(request.getRole());


        Optional<CandidateMarks> candidate = candidateRepository.findByExamineeInfoExamineeId(examineeInfo.get().getExamineeId());

        if (candidate.isEmpty()){
            CandidateMarks candidateMarks = CandidateMarks.builder()
                    .examineeInfo(examineeInfo.get())
                    .build();

            CandidateMarks saved = candidateRepository.save(candidateMarks);
            return ApiResponseMapper.mapToResponseEntityOK(saved);
        }
        else {

            candidate.get().setExamineeInfo(examineeInfo.get());

            CandidateMarks saved = candidateRepository.save(candidate.get());
            return ApiResponseMapper.mapToResponseEntityOK(saved);

        }





    }


}
