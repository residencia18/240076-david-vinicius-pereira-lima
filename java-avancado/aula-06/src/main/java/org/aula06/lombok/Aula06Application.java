package org.aula06.lombok;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Aula06Application {
    public static final Logger log = LoggerFactory.getLogger(Aula06Application.class);
    public static void main(String[] args) {
        SpringApplication.run(Aula06Application.class, args);
        log.info("Application started...");
    }
}
