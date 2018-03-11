package com.example.cityapp;

import java.util.List;

import org.springframework.data.repository.Repository;

public interface CityRepository extends Repository<City, String> {

	List<City> findAll();

	City findByNameIgnoringCase(String name);

}
