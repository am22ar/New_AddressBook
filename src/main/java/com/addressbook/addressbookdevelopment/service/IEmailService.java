package com.addressbook.addressbookdevelopment.service;


import com.addressbook.addressbookdevelopment.dto.ResponseDto;
import com.addressbook.addressbookdevelopment.model.EmailModel;
import org.springframework.http.ResponseEntity;


public interface IEmailService {
    public ResponseEntity<ResponseDto> sendEmail(EmailModel emailModel);
}
