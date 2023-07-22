package com.bjit.tss.controller;

import com.bjit.tss.model.ApiResponse;
import com.bjit.tss.model.FinalTraineeSelectionRequest;
import com.bjit.tss.service.FinalTraineeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/final-trainee")
public class FinalTraineeController {

    private final FinalTraineeService finalTraineeService;

    @GetMapping("/all-passed/{batchCode}")
    public ResponseEntity<ApiResponse<?>> allPassedFinalTrainee (@Valid @PathVariable String batchCode){
        return finalTraineeService.allPassedFinalTrainee(batchCode);
    }

    @GetMapping("/select")
    public ResponseEntity<ApiResponse<?>> selectFinalTrainee (@Valid @RequestBody FinalTraineeSelectionRequest request){
        return finalTraineeService.selectFinalTrainee(request);
    }
}
