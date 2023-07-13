package com.bjit.tss.service;

import com.bjit.tss.model.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    ResponseEntity<ApiResponse<?>> uploadImage(MultipartFile image);

    ResponseEntity<ApiResponse<?>> uploadResume(MultipartFile resume);
}
