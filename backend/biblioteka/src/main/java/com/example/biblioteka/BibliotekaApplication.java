package com.example.biblioteka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BibliotekaApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(BibliotekaApplication.class);
		app.run(args);
	}
}
