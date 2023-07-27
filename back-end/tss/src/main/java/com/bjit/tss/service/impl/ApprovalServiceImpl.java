package com.bjit.tss.service.impl;

import com.bjit.tss.entity.*;
import com.bjit.tss.exception.UserException;
import com.bjit.tss.mapper.ApiResponseMapper;
import com.bjit.tss.model.response.ApiResponse;
import com.bjit.tss.model.request.ApprovalRequest;
import com.bjit.tss.repository.CandidateRepository;
import com.bjit.tss.repository.ExamineeRepository;
import com.bjit.tss.enums.Role;
import com.bjit.tss.service.ApprovalService;
import jakarta.transaction.Transactional;
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
    @Transactional
    public ResponseEntity<ApiResponse<?>> approveApplicant(ApprovalRequest request) {
        Optional<ExamineeInfo> examineeInfo = examineeRepository.findById(request.getExamineeId());
        if (examineeInfo.isEmpty()) {
            throw new UserException("User not found!!!");
        }

        examineeInfo.get().setRole(Role.CANDIDATE);
        Optional<CandidateMarks> candidate = candidateRepository.findByExamineeInfoExamineeId(examineeInfo.get().getExamineeId());
        if (candidate.isEmpty()) {
            CandidateMarks candidateMarks = CandidateMarks.builder()
                    .examineeInfo(examineeInfo.get())
                    .writtenMarks(new WrittenMarks())
                    .technicalViva(new RoundMarks())
                    .aptitudeTest(new RoundMarks())
                    .hrViva(new RoundMarks())
                    .build();
            CandidateMarks saved = candidateRepository.save(candidateMarks);
            return ApiResponseMapper.mapToResponseEntityOK(saved);
        } else {
            candidate.get().setExamineeInfo(examineeInfo.get());
            CandidateMarks saved = candidateRepository.save(candidate.get());

            System.out.println(examineeInfo.get().getUserInfo().getFirstName()+ " was approved by admin whose email is : "+examineeInfo.get().getUserInfo().getEmail());
            return ApiResponseMapper.mapToResponseEntityOK(candidate.get());
        }
    }
}
