package com.senate.transactions;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.function.context.FunctionalSpringApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		FunctionalSpringApplication.run(Application.class, args);
	}

	@Bean
	public Function<String, Boolean> containsCloud() {
		return value -> {
			return value.contains("cloud");
		};
	}





}
