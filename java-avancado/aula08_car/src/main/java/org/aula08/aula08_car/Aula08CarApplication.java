package org.aula08.aula08_car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Aula08CarApplication {
	public static Logger log = LoggerFactory.getLogger(Aula08CarApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(Aula08CarApplication.class, args);
		log.info("Application started ...");
	}

}
