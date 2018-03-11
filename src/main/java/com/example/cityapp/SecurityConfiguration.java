package com.example.cityapp;

import org.springframework.boot.actuate.autoconfigure.security.reactive.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfiguration {

	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
		return http
				.authorizeExchange()
					.matchers(EndpointRequest.toAnyEndpoint()).authenticated()
					.anyExchange().permitAll().and()
				.formLogin().and()
				.httpBasic().and()
			.build();
	}

}
