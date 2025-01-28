package com.addressbook.addressbookdevelopment.service;

import com.addressbook.addressbookdevelopment.dto.ResponseDto;
import com.addressbook.addressbookdevelopment.model.EmailModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public ResponseEntity<ResponseDto> sendEmail(EmailModel email) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(email.getTo());
            mailMessage.setSubject(email.getSubject());
            mailMessage.setText(email.getBody());

            javaMailSender.send(mailMessage);

            ResponseDto responseDTO = new ResponseDto("Sent email ", mailMessage, null);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseDto responseDTO = new ResponseDto("ERROR: Couldn't send email", null, null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
