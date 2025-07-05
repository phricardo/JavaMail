package com.phricardo.email.JavaMail.service;

import static java.util.Optional.of;
import static java.util.Optional.ofNullable;

import com.phricardo.email.JavaMail.model.Email;
import com.phricardo.email.JavaMail.model.EmailBody;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

  @Async
  public void sendEmail(@NonNull final Email email) {
    log.info("Starting sendEmail on thread: {}", Thread.currentThread().getName());
    try {
      val hasHtml = ofNullable(email).map(Email::getBody).map(EmailBody::getHtml).isPresent();

      val content =
          of(email)
              .map(Email::getBody)
              .map(EmailBody::getHtml)
              .orElseGet(() -> of(email).map(Email::getBody).map(EmailBody::getText).orElseThrow());

      val mailSender = createDynamicSender(email);
      val message = mailSender.createMimeMessage();
      val helper = new MimeMessageHelper(message, true, "UTF-8");

      helper.setFrom(email.getFrom());
      helper.setTo(email.getTo());
      helper.setSubject(email.getSubject());
      helper.setText(content, hasHtml);

      mailSender.send(message);
    } catch (final MailSendException ex) {
      log.error("Error sending email to '{}'", email.getTo(), ex);
    } catch (final Exception ex) {
      log.error("Unknown error occurred while sending email", ex);
    }
  }

  private JavaMailSenderImpl createDynamicSender(final Email email) {
    val mailSender = new JavaMailSenderImpl();
    mailSender.setHost(email.getHost());
    mailSender.setPort(email.getPort());
    mailSender.setUsername(email.getUsername());
    mailSender.setPassword(email.getPassword());

    val props = mailSender.getJavaMailProperties();
    props.put("mail.transport.protocol", "smtp");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");

    return mailSender;
  }
}
