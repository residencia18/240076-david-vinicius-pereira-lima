package org.aula07.aula07_car;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Aula07CarApplication {
	public static Logger log = LoggerFactory.getLogger(Aula07CarApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(Aula07CarApplication.class, args);
		log.info("Application started ...");
	}

}
