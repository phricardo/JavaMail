package br.com.phricardo.email.JavaMail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class JavaMailApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaMailApplication.class, args);
	}

}
