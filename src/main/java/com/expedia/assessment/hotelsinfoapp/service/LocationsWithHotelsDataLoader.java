package com.expedia.assessment.hotelsinfoapp.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.expedia.assessment.hotelsinfoapp.model.HotelsAtLocation;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class LocationsWithHotelsDataLoader {

	private static final Logger logger = LoggerFactory.getLogger(LocationsWithHotelsDataLoader.class);

	@Cacheable("hotelsInfo")
	public List<HotelsAtLocation> loadAndGetLocationsAndTheirHotels() throws IOException {
		logger.info("Entering loadAndGetLocationsAndTheirHotels");
		List<HotelsAtLocation> locations = new ArrayList<HotelsAtLocation>();
		ObjectMapper mapper = new ObjectMapper();
		HotelsAtLocation hotelsAtLocation = null;
		InputStream inputStream = new ClassPathResource("static/json_input_data.json").getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		hotelsAtLocation = mapper.readValue(br, HotelsAtLocation.class);
		logger.info("Finished loading hotels for " + hotelsAtLocation.getName() + " city");
		locations.add(hotelsAtLocation);
		return locations;
	}

	public Optional<HotelsAtLocation> getAllHotelsForALocation(Integer locationId) throws IOException {
		return this.loadAndGetLocationsAndTheirHotels().stream()
				.filter(hotelsAtLocation -> hotelsAtLocation.getLocation_id() == locationId).findAny();
	}

}
