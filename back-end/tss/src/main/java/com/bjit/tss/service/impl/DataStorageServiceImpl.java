package com.bjit.tss.service.impl;

import com.bjit.tss.entity.DataStorage;
import com.bjit.tss.mapper.ApiResponseMapper;
import com.bjit.tss.model.ApiResponse;
import com.bjit.tss.model.DataStorageRequest;
import com.bjit.tss.repository.DataStorageRepository;
import com.bjit.tss.service.DataStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DataStorageServiceImpl implements DataStorageService {

    private final DataStorageRepository dataStorageRepository;
    @Override
    public ResponseEntity<ApiResponse<?>> setDataStorage(DataStorageRequest request) {
        Optional<DataStorage> dataStorage1 = dataStorageRepository.findByDataKey(request.getDataKey());
        if (dataStorage1.isPresent()) {
            dataStorage1.get().setDataValue(request.getDataValue());
            DataStorage savedData = dataStorageRepository.save(dataStorage1.get());
            return ApiResponseMapper.mapToResponseEntityOK(savedData,"Data updated successfully.");

        } else {
            DataStorage dataStorage = DataStorage.builder()
                    .dataKey(request.getDataKey())
                    .dataValue(request.getDataValue())
                    .build();
            DataStorage  savedData = dataStorageRepository.save(dataStorage);
            return ApiResponseMapper.mapToResponseEntityCreated(savedData,"Data added successfully.");

        }

    }
}