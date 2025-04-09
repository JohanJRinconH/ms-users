package com.johanrincon.ms.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@PropertySources({
		@PropertySource("classpath:bbdd.properties")
})
@EnableJpaRepositories(basePackages = "com.johanrincon.ms.users.repositories")
@EntityScan(basePackages = "com.johanrincon.ms.users.entities")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
