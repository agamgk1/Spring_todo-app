package com.spring.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import javax.validation.Validator;

@SpringBootApplication
public class TodoAppApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(TodoAppApplication.class, args);
	}

	@Bean
	Validator validator() { //obiekt validator bedzie zarzadzalny przez springs dizieki adnotacjii bean. W klasie z konfoguracjia (rowniez z adnotaja SpringBootApplication) powinno znalezc sie cos takiego
		return new LocalValidatorFactoryBean();
		
	}
	}

