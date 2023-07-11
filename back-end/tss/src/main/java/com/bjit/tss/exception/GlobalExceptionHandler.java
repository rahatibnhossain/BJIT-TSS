package com.bjit.tss.exception;

import com.bjit.tss.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ApiResponse> AuthenticationE(Exception ex){

        return new ResponseEntity<>(ApiResponse.builder().error_message(ex.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<ApiResponse> Authentication(Exception ex){

        return new ResponseEntity<>(ApiResponse.builder().error_message(ex.getMessage()).build(), HttpStatus.UNAUTHORIZED);
    }

}
