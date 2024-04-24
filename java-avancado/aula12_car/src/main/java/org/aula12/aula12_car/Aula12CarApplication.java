package org.aula12.aula12_car;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Aula12CarApplication {
	public static Logger log = LoggerFactory.getLogger(Aula12CarApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(Aula12CarApplication.class, args);
		log.info("Application started ...");
	}

}
