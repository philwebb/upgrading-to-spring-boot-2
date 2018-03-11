package com.example.webinar;

import java.util.List;

import org.springframework.data.repository.Repository;

public interface CityRepository extends Repository<City, String> {

	List<City> findAll();

	City getByNameIgnoringCase(String name);

}
