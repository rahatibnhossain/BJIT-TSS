package com.bjit.tss.service;

import com.bjit.tss.model.AdmitCardRequest;


import java.io.ByteArrayInputStream;

public interface AdmitCardService {
    ByteArrayInputStream generateAdmit(AdmitCardRequest admitCardRequest);
}
