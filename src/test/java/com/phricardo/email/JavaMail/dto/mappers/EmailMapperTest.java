package com.phricardo.email.JavaMail.dto.mappers;

import static org.assertj.core.api.Assertions.assertThat;

import com.phricardo.email.JavaMail.dto.EmailBodyRequest;
import com.phricardo.email.JavaMail.dto.EmailRequest;
import com.phricardo.email.JavaMail.model.Email;
import com.phricardo.email.JavaMail.model.EmailBody;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmailMapperTest {

  private EmailMapper emailMapper;

  @BeforeEach
  void setUp() {
    emailMapper = new EmailMapper();
  }

  @Test
  void shouldConvertEmailRequestToEmailModelCorrectly() {
    // Given: a valid EmailRequest with nested EmailBodyRequest
    val bodyRequest =
        EmailBodyRequest.builder().text("Plain text content").html("<p>HTML content</p>").build();

    val request =
        EmailRequest.builder()
            .host("smtp.example.com")
            .port(587)
            .username("user")
            .password("pass")
            .from("sender@example.com")
            .to("recipient@example.com")
            .subject("Test Subject")
            .body(bodyRequest)
            .build();

    // When: mapping to Email model
    val result = emailMapper.toModel(request);

    // Then: all fields should be correctly mapped
    assertThat(result).isNotNull();
    assertThat(result.getHost()).isEqualTo("smtp.example.com");
    assertThat(result.getUsername()).isEqualTo("user");
    assertThat(result.getPassword()).isEqualTo("pass");
    assertThat(result.getPort()).isEqualTo(587);
    assertThat(result.getFrom()).isEqualTo("sender@example.com");
    assertThat(result.getTo()).isEqualTo("recipient@example.com");
    assertThat(result.getSubject()).isEqualTo("Test Subject");

    assertThat(result.getBody()).isNotNull();
    assertThat(result.getBody().getText()).isEqualTo("Plain text content");
    assertThat(result.getBody().getHtml()).isEqualTo("<p>HTML content</p>");
  }

  @Test
  void shouldConvertEmailModelToEmailRequestCorrectly() {
    // Given: a valid Email model with nested EmailBody
    val body = EmailBody.builder().text("Plain text content").html("<p>HTML content</p>").build();

    val model =
        Email.builder()
            .host("smtp.example.com")
            .port(587)
            .username("user")
            .password("pass")
            .from("sender@example.com")
            .to("recipient@example.com")
            .subject("Test Subject")
            .body(body)
            .build();

    // When: mapping to EmailRequest DTO
    val result = emailMapper.toRequest(model);

    // Then: all fields should be correctly mapped
    assertThat(result).isNotNull();
    assertThat(result.getHost()).isEqualTo("smtp.example.com");
    assertThat(result.getUsername()).isEqualTo("user");
    assertThat(result.getPassword()).isEqualTo("pass");
    assertThat(result.getPort()).isEqualTo(587);
    assertThat(result.getFrom()).isEqualTo("sender@example.com");
    assertThat(result.getTo()).isEqualTo("recipient@example.com");
    assertThat(result.getSubject()).isEqualTo("Test Subject");

    assertThat(result.getBody()).isNotNull();
    assertThat(result.getBody().getText()).isEqualTo("Plain text content");
    assertThat(result.getBody().getHtml()).isEqualTo("<p>HTML content</p>");
  }
}
