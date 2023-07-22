package com.bjit.tss.controller;

import com.bjit.tss.model.ApiResponse;
import com.bjit.tss.model.DataStorageRequest;
import com.bjit.tss.repository.DataStorageRepository;
import com.bjit.tss.service.DataStorageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/data-storage")
public class DataStorageController {

    private final DataStorageService dataStorageService;

    @PostMapping("/set")
    public ResponseEntity<ApiResponse<?>> setDataStorage(@Valid @RequestBody DataStorageRequest dataStorageRequest) {
        return dataStorageService.setDataStorage(dataStorageRequest);
    }
}