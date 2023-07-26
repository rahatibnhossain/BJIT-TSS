package com.bjit.tss.service;

import com.bjit.tss.model.request.AdmitCardRequest;

import java.io.ByteArrayInputStream;

public interface AdmitCardService {

    ByteArrayInputStream generateAdmit(String examineeId);
}