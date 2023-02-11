package br.com.phricardo.email.JavaMail.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Email {

    private String to;
    private String subject;
    private String text;
}
