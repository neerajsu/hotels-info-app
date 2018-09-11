package com.expedia.assessment.hotelsinfoapp.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.expedia.assessment.hotelsinfoapp.constants.SortOptionEnum;
import com.expedia.assessment.hotelsinfoapp.exception.ServiceException;
import com.expedia.assessment.hotelsinfoapp.model.Hotel;
import com.expedia.assessment.hotelsinfoapp.service.HotelsInfoService;
import com.expedia.assessment.hotelsinfoapp.service.HotelsInfoServiceImpl;
import com.expedia.assessment.hotelsinfoapp.service.LocationsWithHotelsDataLoader;

@RunWith(SpringRunner.class)
@ContextConfiguration
public class HotelsInfoServiceImplTests {

	@TestConfiguration
	static class HotelsInfoServiceImplTestContextConfiguration {

		@Bean
		public HotelsInfoService hotelsInfoService() {
			return new HotelsInfoServiceImpl();
		}

		@Bean
		public LocationsWithHotelsDataLoader locationsWithHotelsDataLoader() {
			return new LocationsWithHotelsDataLoader();
		}
	}

	@Autowired
	private HotelsInfoService hotelsInfoService;

	@Test
	public void testGetHotelsNearLocationWithPriceAscendingSort() throws ServiceException {
		List<Hotel> hotels = this.hotelsInfoService.getHotelsNearLocation(1, SortOptionEnum.PRICE, true);
		List<String> hotelNames = new ArrayList<>();
		for (Hotel hotel : hotels) {
			hotelNames.add(hotel.getName());
		}
		assertEquals("When sorting hotels with price in ascending order", hotelNames,
				new ArrayList<String>(Arrays.asList("Bespin Level 1 at Cloud City", "Crowne Plaza of Betazed",
						"aloft Caprica City Center", "Hyatt Place of Babylon 5", "DoubleTree of Manassas, Reach")));
	}

	@Test
	public void testGetHotelsNearLocationWithPriceDescendingSort() throws ServiceException {
		List<Hotel> hotels = this.hotelsInfoService.getHotelsNearLocation(1, SortOptionEnum.PRICE, false);
		List<String> hotelNames = new ArrayList<>();
		for (Hotel hotel : hotels) {
			hotelNames.add(hotel.getName());
		}
		assertEquals("When sorting hotels with price in descending order", hotelNames,
				new ArrayList<String>(Arrays.asList("Hyatt Place of Babylon 5", "DoubleTree of Manassas, Reach",
						"aloft Caprica City Center", "Crowne Plaza of Betazed", "Bespin Level 1 at Cloud City")));
	}

	@Test
	public void testGetHotelsNearLocationWithNameAscendingSort() throws ServiceException {
		List<Hotel> hotels = this.hotelsInfoService.getHotelsNearLocation(1, SortOptionEnum.NAME, true);
		List<String> hotelNames = new ArrayList<>();
		for (Hotel hotel : hotels) {
			hotelNames.add(hotel.getName());
		}
		assertEquals("When sorting hotels with Name in Ascending order", hotelNames,
				new ArrayList<String>(Arrays.asList("aloft Caprica City Center", "Bespin Level 1 at Cloud City",
						"Crowne Plaza of Betazed", "DoubleTree of Manassas, Reach", "Hyatt Place of Babylon 5")));
	}

	@Test
	public void testGetHotelsNearLocationWithNameDescendingSort() throws ServiceException {
		List<Hotel> hotels = this.hotelsInfoService.getHotelsNearLocation(1, SortOptionEnum.NAME, false);
		List<String> hotelNames = new ArrayList<>();
		for (Hotel hotel : hotels) {
			hotelNames.add(hotel.getName());
		}
		assertEquals("When sorting hotels with Name in descending order", hotelNames,
				new ArrayList<String>(Arrays.asList("Hyatt Place of Babylon 5", "DoubleTree of Manassas, Reach",
						"Crowne Plaza of Betazed", "Bespin Level 1 at Cloud City", "aloft Caprica City Center")));
	}

	@Test
	public void testGetHotelsNearLocationWithDistanceAscendingSort() throws ServiceException {
		List<Hotel> hotels = this.hotelsInfoService.getHotelsNearLocation(1, SortOptionEnum.DISTANCE, true);
		List<String> hotelNames = new ArrayList<>();
		for (Hotel hotel : hotels) {
			hotelNames.add(hotel.getName());
		}
		assertEquals("When sorting hotels with Distance in Ascending order", hotelNames,
				new ArrayList<String>(Arrays.asList("Hyatt Place of Babylon 5", "Crowne Plaza of Betazed",
						"aloft Caprica City Center", "Bespin Level 1 at Cloud City", "DoubleTree of Manassas, Reach")));
	}

	@Test
	public void testGetHotelsNearLocationWithDistanceDescendingSort() throws ServiceException {
		List<Hotel> hotels = this.hotelsInfoService.getHotelsNearLocation(1, SortOptionEnum.DISTANCE, false);
		List<String> hotelNames = new ArrayList<>();
		for (Hotel hotel : hotels) {
			hotelNames.add(hotel.getName());
		}
		assertEquals("When sorting hotels with Distance in descending order", hotelNames,
				new ArrayList<String>(Arrays.asList("DoubleTree of Manassas, Reach", "Bespin Level 1 at Cloud City",
						"aloft Caprica City Center", "Crowne Plaza of Betazed", "Hyatt Place of Babylon 5")));
	}

	@Test
	public void testGetHotelsNearLocationWithRatingAscendingSort() throws ServiceException {
		List<Hotel> hotels = this.hotelsInfoService.getHotelsNearLocation(1, SortOptionEnum.RATING, true);
		List<String> hotelNames = new ArrayList<>();
		for (Hotel hotel : hotels) {
			hotelNames.add(hotel.getName());
		}
		assertEquals("When sorting hotels with Rating in Ascending order", hotelNames,
				new ArrayList<String>(Arrays.asList("Bespin Level 1 at Cloud City", "Hyatt Place of Babylon 5",
						"DoubleTree of Manassas, Reach", "Crowne Plaza of Betazed", "aloft Caprica City Center")));
	}

	@Test
	public void testGetHotelsNearLocationWithRatingDescendingSort() throws ServiceException {
		List<Hotel> hotels = this.hotelsInfoService.getHotelsNearLocation(1, SortOptionEnum.RATING, false);
		List<String> hotelNames = new ArrayList<>();
		for (Hotel hotel : hotels) {
			hotelNames.add(hotel.getName());
		}
		assertEquals("When sorting hotels with Rating in descending order", hotelNames,
				new ArrayList<String>(Arrays.asList("aloft Caprica City Center", "Crowne Plaza of Betazed",
						"DoubleTree of Manassas, Reach", "Hyatt Place of Babylon 5", "Bespin Level 1 at Cloud City")));
	}

}
