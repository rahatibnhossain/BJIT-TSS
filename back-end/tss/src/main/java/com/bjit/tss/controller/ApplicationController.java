package com.bjit.tss.controller;

import com.bjit.tss.model.ApiResponse;
import com.bjit.tss.model.ApplicationRequest;
import com.bjit.tss.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/application")
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;

    @PostMapping("/apply")
    public ResponseEntity<ApiResponse<?>> applyCourse(@RequestBody ApplicationRequest applicationRequest){
        return applicationService.applyCourse(applicationRequest);
    }

    @GetMapping("/course/{batchCode}")
    public ResponseEntity<ApiResponse<?>> allApplicationSpecific(@PathVariable String batchCode){
        return applicationService.allApplicationSpecific(batchCode);
    }

}
