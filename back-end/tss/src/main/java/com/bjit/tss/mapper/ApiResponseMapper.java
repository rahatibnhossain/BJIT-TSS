package com.bjit.tss.mapper;

import com.bjit.tss.entity.CourseInfo;
import com.bjit.tss.model.ApiResponse;
import com.bjit.tss.model.CourseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponseMapper {


    public static ResponseEntity<ApiResponse<?>> mapToResponseEntityOK(Object object){

        return new ResponseEntity<ApiResponse<?>>(ApiResponse
                .builder()
                .data(object)
                .build(), HttpStatus.OK);
    }

    public static ResponseEntity<ApiResponse<?>> mapToResponseEntityCreated(Object object){

        return new ResponseEntity<ApiResponse<?>>(ApiResponse
                .builder()
                .data(object)
                .build(), HttpStatus.CREATED);
    }

    public static ResponseEntity<ApiResponse<?>> mapToResponseEntityUnauthorized(Object object){

        return new ResponseEntity<ApiResponse<?>>(ApiResponse
                .builder()
                .error_message(object)
                .build(), HttpStatus.UNAUTHORIZED);
    }

    public static ResponseEntity<ApiResponse<?>> mapToResponseEntityBadRequest(Object object){

        return new ResponseEntity<ApiResponse<?>>(ApiResponse
                .builder()
                .error_message(object)
                .build(), HttpStatus.BAD_REQUEST);
    }
    public static ResponseEntity<ApiResponse<?>> mapToResponseEntityUnsupported(Object object){

        return new ResponseEntity<ApiResponse<?>>(ApiResponse
                .builder()
                .error_message(object)
                .build(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

}
