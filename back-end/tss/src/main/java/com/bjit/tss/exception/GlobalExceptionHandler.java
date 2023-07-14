package com.bjit.tss.exception;

import com.bjit.tss.mapper.ApiResponseMapper;
import com.bjit.tss.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ApiResponse<?>> AllEx(Exception ex) {
        return ApiResponseMapper.mapToResponseEntityBadRequest(ex.getMessage());
    }
    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<ApiResponse<?>> AuthenticationEx(Exception ex) {
        return ApiResponseMapper.mapToResponseEntityUnauthorized(ex.getMessage());
    }

    @ExceptionHandler({CourseException.class})
    public ResponseEntity<ApiResponse<?>> CourseEX(Exception ex) {

        return ApiResponseMapper.mapToResponseEntityBadRequest(ex.getMessage());
    }

    @ExceptionHandler({FileUploadException.class})
    public ResponseEntity<ApiResponse<?>> FileUploadEX(Exception ex) {

        return ApiResponseMapper.mapToResponseEntityUnsupported(ex.getMessage());
    }

}
