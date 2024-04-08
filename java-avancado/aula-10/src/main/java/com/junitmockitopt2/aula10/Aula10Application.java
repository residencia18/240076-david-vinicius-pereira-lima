package com.junitmockitopt2.aula10;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Aula10Application {
    public static final Logger log = LoggerFactory.getLogger(Aula10Application.class);
    public static void main(String[] args) {
        SpringApplication.run(Aula10Application.class, args);
        log.info("Application started ...");
    }

}
