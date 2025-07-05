package com.phricardo.email.JavaMail.controller;

import com.phricardo.email.JavaMail.model.Email;
import com.phricardo.email.JavaMail.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.ACCEPTED;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/email")
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/send")
    @ResponseStatus(ACCEPTED)
    public void sendEmail(@RequestBody Email email) {
        emailService.sendEmail(email);
    }
}