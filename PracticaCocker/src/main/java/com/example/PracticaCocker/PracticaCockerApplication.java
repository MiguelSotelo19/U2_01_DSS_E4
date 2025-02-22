package com.example.PracticaCocker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.example.PracticaCocker.model")
@EnableJpaRepositories(basePackages = "com.example.PracticaCocker.model.usuario")
public class PracticaCockerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticaCockerApplication.class, args);
	}

}
