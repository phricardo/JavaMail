package com.phricardo.email.JavaMail.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Email {

    private String host;
    private String username;
    private String password;
    private int port = 587;
    private String from;
    private String to;
    private String subject;
    private EmailBody body;
}
