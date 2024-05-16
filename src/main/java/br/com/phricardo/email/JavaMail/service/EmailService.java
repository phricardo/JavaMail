package br.com.phricardo.email.JavaMail.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.properties.mail.from}")
    public String from;

    public void sendEmail(final String to, final String subject, final String text) {
        val msg = new SimpleMailMessage();

        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(text);
        msg.setFrom(from);

        javaMailSender.send(msg);
    }
}
