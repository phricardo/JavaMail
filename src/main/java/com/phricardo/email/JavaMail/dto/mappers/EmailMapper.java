package com.phricardo.email.JavaMail.dto.mappers;

import static java.util.Objects.isNull;

import com.phricardo.email.JavaMail.dto.EmailBodyRequest;
import com.phricardo.email.JavaMail.dto.EmailRequest;
import com.phricardo.email.JavaMail.model.Email;
import com.phricardo.email.JavaMail.model.EmailBody;
import org.springframework.stereotype.Component;

@Component
public class EmailMapper {

  public Email toModel(final EmailRequest request) {
    return Email.builder()
        .host(request.getHost())
        .username(request.getUsername())
        .password(request.getPassword())
        .port(request.getPort())
        .from(request.getFrom())
        .to(request.getTo())
        .subject(request.getSubject())
        .body(toModel(request.getBody()))
        .build();
  }

  public EmailBody toModel(final EmailBodyRequest request) {
    if (isNull(request)) return null;
    return EmailBody.builder().text(request.getText()).html(request.getHtml()).build();
  }

  public EmailRequest toRequest(final Email model) {
    return EmailRequest.builder()
        .host(model.getHost())
        .username(model.getUsername())
        .password(model.getPassword())
        .port(model.getPort())
        .from(model.getFrom())
        .to(model.getTo())
        .subject(model.getSubject())
        .body(toRequest(model.getBody()))
        .build();
  }

  public EmailBodyRequest toRequest(final EmailBody model) {
    if (isNull(model)) return null;
    return EmailBodyRequest.builder().text(model.getText()).html(model.getHtml()).build();
  }
}
