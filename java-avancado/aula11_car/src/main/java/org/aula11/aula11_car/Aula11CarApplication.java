package org.aula11.aula11_car;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Aula11CarApplication {
	public static Logger log = LoggerFactory.getLogger(Aula11CarApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(Aula11CarApplication.class, args);
		log.info("Application started ...");
	}

}
