package com.bjit.tss.controller;

import com.bjit.tss.entity.LoginInfo;
import com.bjit.tss.model.AdmitCardRequest;
import com.bjit.tss.model.ApiResponse;
import com.bjit.tss.service.AdmitCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/candidate")
public class CandidateController {
    private final AdmitCardService admitCardService;


    @PostMapping("generate-admit")
    public ResponseEntity<InputStreamResource> generateAdmitCard(@RequestBody AdmitCardRequest admitCardRequest){


        ByteArrayInputStream pdf= admitCardService.generateAdmit(admitCardRequest);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Description", "inline;file=AdmitCard.pdf");
        return ResponseEntity
                .ok()
                .headers(httpHeaders)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdf));
    }
}


