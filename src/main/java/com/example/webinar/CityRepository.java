package com.example.webinar;

import org.springframework.data.repository.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CityRepository extends Repository<City, String> {

	Flux<City> findAll();

	Mono<City> findByNameIgnoringCase(String name);

}
