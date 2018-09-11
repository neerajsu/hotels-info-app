package com.expedia.assessment.hotelsinfoapp.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class HotelsInfoAppApplicationIntegrationTests {

	@LocalServerPort
	private int port;
	
	 @Autowired
	 private MockMvc mvc;

	TestRestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void testApplicationResourceHealth() {
		ResponseEntity<String> response = restTemplate.getForEntity(createURLWithPort("location/ping"), String.class);
		assertEquals("Application is up and running", response.getBody());
	}

	@Test
	public void testRetrieveHotelsBySortOnNameAndAscending() throws Exception {
		mvc.perform(get("/location/1/hotels?sort_by=NAME&is_ascending=true")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content()
			      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			      .andExpect(jsonPath("$[0].name", is("aloft Caprica City Center")))
			      .andExpect(jsonPath("$[1].name", is("Bespin Level 1 at Cloud City")))
			      .andExpect(jsonPath("$[2].name", is("Crowne Plaza of Betazed")))
			      .andExpect(jsonPath("$[3].name", is("DoubleTree of Manassas, Reach")))
			      .andExpect(jsonPath("$[4].name", is("Hyatt Place of Babylon 5")));
	}
	
	@Test
	public void testRetrieveHotelsBySortOnNameAndDescending() throws Exception {
		mvc.perform(get("/location/1/hotels?sort_by=NAME&is_ascending=false")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content()
			      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			      .andExpect(jsonPath("$[0].name", is("Hyatt Place of Babylon 5")))
			      .andExpect(jsonPath("$[1].name", is("DoubleTree of Manassas, Reach")))
			      .andExpect(jsonPath("$[2].name", is("Crowne Plaza of Betazed")))
			      .andExpect(jsonPath("$[3].name", is("Bespin Level 1 at Cloud City")))
			      .andExpect(jsonPath("$[4].name", is("aloft Caprica City Center")));
	}
	
	@Test
	public void testRetrieveHotelsBySortOnPriceAndAscending() throws Exception {
		mvc.perform(get("/location/1/hotels?sort_by=PRICE&is_ascending=true")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content()
			      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			      .andExpect(jsonPath("$[0].name", is("Bespin Level 1 at Cloud City")))
			      .andExpect(jsonPath("$[1].name", is("Crowne Plaza of Betazed")))
			      .andExpect(jsonPath("$[2].name", is("aloft Caprica City Center")))
			      .andExpect(jsonPath("$[3].name", is("Hyatt Place of Babylon 5")))
			      .andExpect(jsonPath("$[4].name", is("DoubleTree of Manassas, Reach")));
	}
	
	@Test
	public void testRetrieveHotelsBySortOnPriceAndDescending() throws Exception {
		mvc.perform(get("/location/1/hotels?sort_by=PRICE&is_ascending=false")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content()
			      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			      .andExpect(jsonPath("$[0].name", is("Hyatt Place of Babylon 5")))
			      .andExpect(jsonPath("$[1].name", is("DoubleTree of Manassas, Reach")))
			      .andExpect(jsonPath("$[2].name", is("aloft Caprica City Center")))
			      .andExpect(jsonPath("$[3].name", is("Crowne Plaza of Betazed")))
			      .andExpect(jsonPath("$[4].name", is("Bespin Level 1 at Cloud City")));
	}
	
	@Test
	public void testRetrieveHotelsBySortOnDistanceAndAscending() throws Exception {
		mvc.perform(get("/location/1/hotels?sort_by=DISTANCE&is_ascending=true")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content()
			      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			      .andExpect(jsonPath("$[0].name", is("Hyatt Place of Babylon 5")))
			      .andExpect(jsonPath("$[1].name", is("Crowne Plaza of Betazed")))
			      .andExpect(jsonPath("$[2].name", is("aloft Caprica City Center")))
			      .andExpect(jsonPath("$[3].name", is("Bespin Level 1 at Cloud City")))
			      .andExpect(jsonPath("$[4].name", is("DoubleTree of Manassas, Reach")));
	}
	
	@Test
	public void testRetrieveHotelsBySortOnDistanceAndDescending() throws Exception {
		mvc.perform(get("/location/1/hotels?sort_by=DISTANCE&is_ascending=false")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content()
			      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			      .andExpect(jsonPath("$[0].name", is("DoubleTree of Manassas, Reach")))
			      .andExpect(jsonPath("$[1].name", is("Bespin Level 1 at Cloud City")))
			      .andExpect(jsonPath("$[2].name", is("aloft Caprica City Center")))
			      .andExpect(jsonPath("$[3].name", is("Crowne Plaza of Betazed")))
			      .andExpect(jsonPath("$[4].name", is("Hyatt Place of Babylon 5")));
	}
	
	@Test
	public void testRetrieveHotelsBySortOnRatingAndAscending() throws Exception {
		mvc.perform(get("/location/1/hotels?sort_by=RATING&is_ascending=true")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content()
			      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			      .andExpect(jsonPath("$[0].name", is("Bespin Level 1 at Cloud City")))
			      .andExpect(jsonPath("$[1].name", is("Hyatt Place of Babylon 5")))
			      .andExpect(jsonPath("$[2].name", is("DoubleTree of Manassas, Reach")))
			      .andExpect(jsonPath("$[3].name", is("Crowne Plaza of Betazed")))
			      .andExpect(jsonPath("$[4].name", is("aloft Caprica City Center")));
	}
	
	@Test
	public void testRetrieveHotelsBySortOnRatingAndDescending() throws Exception {
		mvc.perform(get("/location/1/hotels?sort_by=RATING&is_ascending=false")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content()
			      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			      .andExpect(jsonPath("$[0].name", is("aloft Caprica City Center")))
			      .andExpect(jsonPath("$[1].name", is("Crowne Plaza of Betazed")))
			      .andExpect(jsonPath("$[2].name", is("DoubleTree of Manassas, Reach")))
			      .andExpect(jsonPath("$[3].name", is("Hyatt Place of Babylon 5")))
			      .andExpect(jsonPath("$[4].name", is("Bespin Level 1 at Cloud City")));
	}


	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
