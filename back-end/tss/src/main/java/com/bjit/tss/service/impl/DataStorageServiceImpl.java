package com.bjit.tss.service.impl;

import com.bjit.tss.entity.DataStorage;
import com.bjit.tss.entity.ExamineeInfo;
import com.bjit.tss.entity.LoginInfo;
import com.bjit.tss.exception.AdmitGenerationException;
import com.bjit.tss.exception.UserException;
import com.bjit.tss.mapper.ApiResponseMapper;
import com.bjit.tss.model.AdmitCardRequest;
import com.bjit.tss.model.ApiResponse;
import com.bjit.tss.model.DataStorageRequest;
import com.bjit.tss.repository.DataStorageRepository;
import com.bjit.tss.repository.ExamineeRepository;
import com.bjit.tss.role.Role;
import com.bjit.tss.service.AdmitCardService;
import com.bjit.tss.service.DataStorageService;
import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Objects;
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