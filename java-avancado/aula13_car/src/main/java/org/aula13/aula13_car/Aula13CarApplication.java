package org.aula13.aula13_car;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Aula13CarApplication {
	private static final Logger logger = LoggerFactory.getLogger(Aula13CarApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(Aula13CarApplication.class, args);
		logger.info("Application started ...");
	}

}
