package com.example.cityapp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class CityController {

	private final CityRepository repository;

	public CityController(CityRepository repository) {
		this.repository = repository;
	}

	@GetMapping(path = "/cities")
	public List<City> all() {
		return this.repository.findAll().stream().filter(this::isInUsa)
				.collect(Collectors.toList());
	}

	@GetMapping(path = "/city/{name}")
	public City byName(@PathVariable String name) {
		return this.repository.findByNameIgnoringCase(name);
	}

	private boolean isInUsa(City city) {
		return city.getCountry().equals("USA");
	}

}
