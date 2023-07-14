package com.bjit.tss.service.impl;

import com.bjit.tss.entity.DataStorage;
import com.bjit.tss.entity.LoginInfo;
import com.bjit.tss.exception.FileUploadException;
import com.bjit.tss.mapper.ApiResponseMapper;
import com.bjit.tss.model.ApiResponse;
import com.bjit.tss.model.FileUploadResponse;
import com.bjit.tss.model.QuestionNumberRequest;
import com.bjit.tss.repository.DataStorageRepository;
import com.bjit.tss.repository.LoginRepository;
import com.bjit.tss.service.FileService;
import com.bjit.tss.service.QuestionNumberService;
import com.bjit.tss.utils.FileUploaderUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionNumberServiceImpl implements QuestionNumberService {

    private final DataStorageRepository dataStorageRepository;
    @Override
    public ResponseEntity<ApiResponse<?>> setWrittenQuestionNumber(QuestionNumberRequest questionNumberRequest) {

        Optional<DataStorage> dataStorage1= dataStorageRepository.findByDataKey("WrittenQuestionNumber");
        if (dataStorage1.isPresent()){
            dataStorage1.get().setDataValue(String.valueOf(questionNumberRequest.getQuestionNumbers()));
            DataStorage saved = dataStorageRepository.save(dataStorage1.get());
            return ApiResponseMapper.mapToResponseEntityCreated(saved);

        }
        else {
            DataStorage dataStorage = DataStorage.builder()
                    .dataKey(questionNumberRequest.getQuestionType())
                    .dataValue(String.valueOf(questionNumberRequest.getQuestionNumbers()))
                    .build();

            DataStorage saved = dataStorageRepository.save(dataStorage);
            return ApiResponseMapper.mapToResponseEntityCreated(saved);

        }


    }
}
