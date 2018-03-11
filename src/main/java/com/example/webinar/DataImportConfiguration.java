package com.example.webinar;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.bind.PropertySourcesPropertyValues;
import org.springframework.boot.bind.RelaxedDataBinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoOperations;

@Configuration
public class DataImportConfiguration {

	@Bean
	public CommandLineRunner initData(MongoOperations mongo) {
		return (String... args) -> {
			mongo.dropCollection(City.class);
			mongo.createCollection(City.class);
			getCities().forEach(mongo::save);
		};
	}

	private List<City> getCities() {
		Properties yaml = loadCitiesYaml();
		MutablePropertySources propertySources = new MutablePropertySources();
		propertySources.addFirst(new PropertiesPropertySource("cities", yaml));
		Cities cities = new Cities();
		RelaxedDataBinder binder = new RelaxedDataBinder(cities);
		binder.bind(new PropertySourcesPropertyValues(propertySources));
		return cities.getCities();
	}

	private Properties loadCitiesYaml() {
		YamlPropertiesFactoryBean properties = new YamlPropertiesFactoryBean();
		properties.setResources(new ClassPathResource("cities.yml"));
		return properties.getObject();
	}

	static class Cities {

		private List<City> cities = new ArrayList<>();

		public List<City> getCities() {
			return cities;
		}

		public void setCities(List<City> cities) {
			this.cities = cities;
		}

	}

}
