package org.aula09.mokito_junit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Aula09Application {
	public static final Logger log = LoggerFactory.getLogger(Aula09Application.class);
	public static void main(String[] args) {
		SpringApplication.run(Aula09Application.class, args);
		log.info("Application started...");
	}

}
