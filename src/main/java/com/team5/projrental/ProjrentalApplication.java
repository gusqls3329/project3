package com.team5.projrental;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.awt.print.Pageable;

@EnableScheduling
@SpringBootApplication
@ConfigurationPropertiesScan
@EnableConfigurationProperties
public class ProjrentalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjrentalApplication.class, args);
	}

	@Bean
	public PageableHandlerMethodArgumentResolverCustomizer customizer(){
		return p -> p.setOneIndexedParameters(true);
	}

	@Bean
	public InMemoryHttpExchangeRepository exchangeRepository() {
		return new InMemoryHttpExchangeRepository();
	}

	@Bean
	public JPAQueryFactory jpaQueryFactory(EntityManager em) {
		return new JPAQueryFactory(em);
	}

}
