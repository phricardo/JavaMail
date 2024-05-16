package br.com.phricardo.email.JavaMail.service;

import br.com.phricardo.email.JavaMail.model.Email;
import br.com.phricardo.email.JavaMail.model.EmailBody;
import jakarta.mail.MessagingException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Optional.of;
import static java.util.Optional.ofNullable;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.properties.mail.from}")
    public String from;

    public void sendEmail(@NonNull final Email email) {
        try {
            val hasHtml = of(email)
                    .map(Email::getBody)
                    .map(EmailBody::getHtml)
                    .isPresent();

            val content = of(email)
                    .map(Email::getBody)
                    .map(EmailBody::getHtml)
                    .orElseGet(() -> of(email)
                            .map(Email::getBody)
                            .map(EmailBody::getText)
                            .orElseThrow());

            val message = javaMailSender.createMimeMessage();
            val helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(from);
            helper.setTo(email.getTo());
            helper.setSubject(email.getSubject());
            helper.setText(content, hasHtml);

            javaMailSender.send(message);

        } catch (final MailSendException ex) {
            log.error("Error sending email to '{}'", email.getTo(), ex);
        } catch (final Exception ex) {
            log.error("Unknown error occurred while sending email", ex);
        }
    }
}
