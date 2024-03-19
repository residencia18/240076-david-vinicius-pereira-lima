package org.aula03.test_swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@OpenAPIDefinition(info = @Info(title = "Teste Swagger", version = "1", description = "API teste para aula 03"))
@SpringBootApplication
public class Aula03Application {

	public static void main(String[] args) {
		SpringApplication.run(Aula03Application.class, args);
	}

}
