package com.example.webinar;

import io.micrometer.core.instrument.MeterRegistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebinarApplication {

	@Bean
	public MeterRegistryCustomizer<MeterRegistry> commonTags() {
		return (registry) -> registry.config()
				.commonTags("application", "webinar");
	}

	public static void main(String[] args) {
		SpringApplication.run(WebinarApplication.class, args);
	}

}
