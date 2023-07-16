package com.bjit.tss.service.impl;

import com.bjit.tss.entity.ExamineeInfo;
import com.bjit.tss.mapper.ApiResponseMapper;
import com.bjit.tss.model.ApiResponse;
import com.bjit.tss.model.ListResponse;
import com.bjit.tss.repository.ExamineeRepository;
import com.bjit.tss.role.Role;
import com.bjit.tss.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final ExamineeRepository examineeRepository;

    @Override
    public ResponseEntity<ApiResponse<?>> allCandidates() {
        List<ExamineeInfo> examineeInfos = examineeRepository.findByRole(Role.CANDIDATE);
        ListResponse<?> listResponse = ListResponse.builder()
                .listResponse(examineeInfos)
                .dataLength((long) examineeInfos.size())
                .build();
        return ApiResponseMapper.mapToResponseEntityOK(listResponse);
    }
}