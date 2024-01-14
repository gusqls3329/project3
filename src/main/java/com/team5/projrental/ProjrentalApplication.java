package com.team5.projrental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ProjrentalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjrentalApplication.class, args);
	}

}
