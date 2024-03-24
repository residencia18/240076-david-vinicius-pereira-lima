package org.aula08.java_faker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Aula08Application {
	public static final Logger log = LoggerFactory.getLogger(Aula08Application.class);
	public static void main(String[] args) {
		SpringApplication.run(Aula08Application.class, args);
		log.info("Application started...");
	}

}
