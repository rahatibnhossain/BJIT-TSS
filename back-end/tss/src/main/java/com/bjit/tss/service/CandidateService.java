package com.bjit.tss.service;

import com.bjit.tss.model.AdmitCardRequest;
import com.bjit.tss.model.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayInputStream;

public interface CandidateService {

    ResponseEntity<ApiResponse<?>> allCandidates();
}
