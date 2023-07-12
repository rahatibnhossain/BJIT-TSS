package com.bjit.tss.service.impl;

import com.bjit.tss.entity.ExamineeInfo;
import com.bjit.tss.exception.UserException;
import com.bjit.tss.mapper.ApiResponseMapper;
import com.bjit.tss.model.ApiResponse;
import com.bjit.tss.model.ApprovalRequest;
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

    @Override
    public ResponseEntity<ApiResponse<?>> markAs(ApprovalRequest approvalRequest) {
        Optional<ExamineeInfo> examineeInfo = examineeRepository.findById(approvalRequest.getExamineeId());
        if (examineeInfo.isEmpty()){
            throw new UserException("User not found!!!");
        }
        examineeInfo.get().setRole(approvalRequest.getRole());

       ExamineeInfo savedExaminee= examineeRepository.save(examineeInfo.get());
        return ApiResponseMapper.mapToResponseEntityOK(savedExaminee);
    }
}
