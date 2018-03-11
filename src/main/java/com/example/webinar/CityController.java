package com.example.webinar;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class CityController {

	private final CityRepository repository;

	public CityController(CityRepository repository) {
		this.repository = repository;
	}

	@GetMapping(path = "/cities")
	public Flux<City> all() {
		return this.repository.findAll().filter(this::isInUsa);
	}

	@GetMapping(path = "/city/{name}")
	public Mono<City> byName(@PathVariable String name) {
		return this.repository.findByNameIgnoringCase(name);
	}

	private boolean isInUsa(City city) {
		return city.getCountry().equals("USA");
	}

}
