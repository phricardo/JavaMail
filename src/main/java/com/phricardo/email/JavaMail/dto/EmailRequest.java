package com.phricardo.email.JavaMail.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class EmailRequest {

  private String host;
  private String username;
  private String password;
  private int port = 587;
  private String from;
  private String to;
  private String subject;
  private EmailBodyRequest body;
}
