package com.bjit.tss.service.impl;

import com.bjit.tss.entity.ExamineeInfo;
import com.bjit.tss.entity.LoginInfo;
import com.bjit.tss.exception.AdmitGenerationException;
import com.bjit.tss.exception.UserException;
import com.bjit.tss.model.request.AdmitCardRequest;
import com.bjit.tss.repository.ExamineeRepository;
import com.bjit.tss.enums.Role;
import com.bjit.tss.service.AdmitCardService;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdmitCardServiceImpl implements AdmitCardService {

    private final ExamineeRepository examineeRepository;
    private final Logger logger = LoggerFactory.getLogger(AdmitCardServiceImpl.class);

    @Override
    public ByteArrayInputStream generateAdmit(String examineeId) {
        Long examineId = Long.valueOf(examineeId);
        Optional<ExamineeInfo> examineeInfo = examineeRepository.findById(examineId);
        if (examineeInfo.isEmpty()) {
            throw new UserException("User not found");
        }

        LoginInfo loginInfo = (LoginInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!Objects.equals(loginInfo.getUserInfo().getUserId(), examineeInfo.get().getUserInfo().getUserId())) {
            throw new UserException("You are not authorized for this operation.");
        }

        if (examineeInfo.get().getRole() != Role.CANDIDATE) {
            throw new UserException("Your role is not authorized for this operation.");
        }

        logger.info("Creating pdf");
        String title = "Admit Card \nWritten Exam ";
        String name = "Applicant Name : " + examineeInfo.get().getUserInfo().getFirstName() +
                " " + examineeInfo.get().getUserInfo().getLastName();
        String id = "Applicant ID : " + examineeInfo.get().getExamineeId();
        String examTime = "Written Exam Time : " + examineeInfo.get().getCourseInfo().getWrittenExamTime();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Document document = new Document();
            document.open();

            PdfWriter.getInstance(document, out);
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 25, Color.black);
            Paragraph titlePara = new Paragraph(title, titleFont);
            titlePara.setAlignment(Element.ALIGN_CENTER);

            Font bodyFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Color.black);
            Paragraph namePara = new Paragraph(name, bodyFont);
            titlePara.setAlignment(Element.ALIGN_LEFT);

            Paragraph idPara = new Paragraph(id, bodyFont);
            titlePara.setAlignment(Element.ALIGN_LEFT);

            Paragraph examTimePara = new Paragraph(examTime, bodyFont);
            titlePara.setAlignment(Element.ALIGN_LEFT);

            document.add(titlePara);
            document.add(namePara);
            document.add(idPara);
            document.add(examTimePara);

            logger.info("Created pdf");

            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception ex) {
            throw new AdmitGenerationException(ex.getMessage());
        }
    }
}