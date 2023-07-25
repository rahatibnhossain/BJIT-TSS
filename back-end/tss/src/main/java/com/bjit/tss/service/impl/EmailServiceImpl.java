package com.bjit.tss.service.impl;


import com.bjit.tss.model.response.ApiResponse;
import com.bjit.tss.model.request.EmailRequest;
import com.bjit.tss.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    public ResponseEntity<ApiResponse<?>> sendEmail(EmailRequest emailRequest) {
//        try
//        {
//            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false);
//            mimeMessageHelper.setFrom(fromEmail);
//            mimeMessageHelper.setTo(emailRequest.getTo());
//            mimeMessageHelper.setText(emailRequest.getBody());
//            mimeMessageHelper.setSubject(emailRequest.getSubject());
//            javaMailSender.send(mimeMessage);
//            return ApiResponseMapper.mapToResponseEntityOK(null,"Email sent successful.");
//        }
//        catch (Exception ex){
//            throw new EmailException(ex.getMessage());
//        }
        return null;
    }
}
