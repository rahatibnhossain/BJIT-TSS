package com.bjit.tss.service;

import com.bjit.tss.model.ApiResponse;
import com.bjit.tss.model.DataStorageRequest;
import org.springframework.http.ResponseEntity;

public interface DataStorageService {
    ResponseEntity<ApiResponse<?>> setDataStorage(DataStorageRequest dataStorageRequest);
}