package com.bjit.tss.service;

import com.bjit.tss.model.response.ApiResponse;
import com.bjit.tss.model.request.DataStorageRequest;
import org.springframework.http.ResponseEntity;

public interface DataStorageService {
    ResponseEntity<ApiResponse<?>> setDataStorage(DataStorageRequest dataStorageRequest);
}