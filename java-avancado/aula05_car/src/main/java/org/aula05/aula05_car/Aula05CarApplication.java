package org.aula05.aula05_car;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Aula05CarApplication {
	public static final Logger log = LoggerFactory.getLogger(Aula05CarApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(Aula05CarApplication.class, args);
		log.info("Application started ...");
	}

}
