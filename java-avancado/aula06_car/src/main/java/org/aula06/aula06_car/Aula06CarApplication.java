package org.aula06.aula06_car;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Aula06CarApplication {

	public static final Logger log = LoggerFactory.getLogger(Aula06CarApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(Aula06CarApplication.class, args);
		log.info("Application started ...");
	}
}
