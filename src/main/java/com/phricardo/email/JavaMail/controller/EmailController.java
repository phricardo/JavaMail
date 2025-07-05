package com.phricardo.email.JavaMail.controller;

import static org.springframework.http.HttpStatus.ACCEPTED;

import com.phricardo.email.JavaMail.dto.EmailRequest;
import com.phricardo.email.JavaMail.dto.mappers.EmailMapper;
import com.phricardo.email.JavaMail.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/email")
public class EmailController {

  private final EmailMapper emailMapper;
  private final EmailService emailService;

  @PostMapping("/send")
  @ResponseStatus(ACCEPTED)
  public void sendEmail(@RequestBody EmailRequest emailRequest) {
    val email = emailMapper.toModel(emailRequest);
    emailService.sendEmail(email);
  }
}
