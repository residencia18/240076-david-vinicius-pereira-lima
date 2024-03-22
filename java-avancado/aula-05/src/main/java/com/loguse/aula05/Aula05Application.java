package com.loguse.aula05;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Aula05Application {
	public static final Logger log = LoggerFactory.getLogger(Aula05Application.class);
	public static void main(String[] args) {
		SpringApplication.run(Aula05Application.class, args);
		log.info("Application started...");
	}

}
