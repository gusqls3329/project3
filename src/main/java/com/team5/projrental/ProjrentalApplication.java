package com.team5.projrental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@ConfigurationPropertiesScan
public class ProjrentalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjrentalApplication.class, args);
	}

	@Bean
	public InMemoryHttpExchangeRepository exchangeRepository() {
		return new InMemoryHttpExchangeRepository();
	}
}
