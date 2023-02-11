package br.com.phricardo.email.JavaMail.controller;

import br.com.phricardo.email.JavaMail.model.Email;
import br.com.phricardo.email.JavaMail.service.EmailService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/email")
public class EmailController {

    private EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public String sendEmail(@RequestBody Email email) {

        emailService.sendEmail(email.getTo(), email.getSubject(), email.getText());

        return "Email sent successfully!";
    }
}