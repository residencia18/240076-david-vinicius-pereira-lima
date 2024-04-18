package org.aula09.aula09_car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Aula09CarApplication {
	private static Logger log = LoggerFactory.getLogger(Aula09CarApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(Aula09CarApplication.class, args);
		log.info("Application started ...");
	}

}
