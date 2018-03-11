package com.example.cityapp;

import io.micrometer.core.instrument.MeterRegistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CityApplication {

	@Bean
	public MeterRegistryCustomizer<MeterRegistry> commonTags() {
		return (registry) -> registry.config()
				.commonTags("application", "cities");
	}

	public static void main(String[] args) {
		SpringApplication.run(CityApplication.class, args);
	}

}
