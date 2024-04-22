package org.aula10.aula10_car;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Aula10CarApplication {
	private static Logger log = LoggerFactory.getLogger(Aula10CarApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(Aula10CarApplication.class, args);
		log.info("Application started ...");
	}

}
