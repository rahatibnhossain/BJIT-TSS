package com.bjit.tss.service.impl;

import com.bjit.tss.entity.LoginInfo;
import com.bjit.tss.exception.FileUploadException;
import com.bjit.tss.mapper.ApiResponseMapper;
import com.bjit.tss.model.ApiResponse;
import com.bjit.tss.model.FileUploadResponse;
import com.bjit.tss.repository.LoginRepository;
import com.bjit.tss.service.FileService;
import com.bjit.tss.utils.FileUploaderUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileUploaderUtils fileUploaderUtils;
    private final LoginRepository loginRepository;
    @Override
    public ResponseEntity<ApiResponse<?>> uploadImage(MultipartFile image) {
        if (image.isEmpty()){
            throw new FileUploadException("No image is uploaded. Try again");
        }
        if (!Objects.equals(image.getContentType(), "image/jpeg")){
            throw new FileUploadException("Only jpeg type image can be uploaded.");
        }
        LoginInfo loginInfo = (LoginInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Path path= fileUploaderUtils.uploadFile(image, true, String.valueOf(loginInfo.getUserInfo().getUserId()));
        loginInfo.getUserInfo().setPhotoUrl(String.valueOf(path));
        loginRepository.save(loginInfo);
        FileUploadResponse fileUploadResponse = FileUploadResponse.builder()
                .filePath(path)
                .build();
        return ApiResponseMapper.mapToResponseEntityCreated(fileUploadResponse, "Image Uploaded");
    }

    @Override
    public ResponseEntity<ApiResponse<?>> uploadResume(MultipartFile resume) {
        if (resume.isEmpty()){
            throw new FileUploadException("No file is uploaded. Try again");
        }

        if (!Objects.equals(resume.getContentType(), "application/pdf")){
            throw new FileUploadException("Only pdf type image can be uploaded.");
        }

        LoginInfo loginInfo = (LoginInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Path path= fileUploaderUtils.uploadFile(resume, false, String.valueOf(loginInfo.getUserInfo().getUserId()));
        loginInfo.getUserInfo().setResumeUrl(String.valueOf(path));

        loginRepository.save(loginInfo);
        FileUploadResponse fileUploadResponse = FileUploadResponse.builder()
                .filePath(path)
                .build();
        return ApiResponseMapper.mapToResponseEntityCreated(fileUploadResponse,"File Uploaded");
    }
}
