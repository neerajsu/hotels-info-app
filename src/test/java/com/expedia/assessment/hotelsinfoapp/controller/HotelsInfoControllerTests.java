package com.expedia.assessment.hotelsinfoapp.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.expedia.assessment.hotelsinfoapp.model.Hotel;
import com.expedia.assessment.hotelsinfoapp.service.HotelsInfoService;
import com.expedia.assessment.hotelsinfoapp.web.HotelsInfoController;

@RunWith(SpringRunner.class)
@WebMvcTest(value = HotelsInfoController.class, secure = false)
public class HotelsInfoControllerTests {

	@MockBean
	private HotelsInfoService hotelsInfoService;
	
	@Autowired
	private MockMvc mockMvc;
	

	@Test
	public void checkResourceStatus() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/location/ping").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals("Application is up and running", response.getContentAsString());
	}

	@Test
	public void testgetHotelsInfoSuccess() throws Exception {
		Mockito.when(hotelsInfoService.getHotelsNearLocation(Mockito.anyInt(), Mockito.any(), Mockito.anyBoolean())).thenReturn(createHotelsMock());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/location/1/hotels?sort_by=RATING&is_ascending=true").accept(
				MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public void testgetHotelsInfoFailureWhenMissingRequestParameterIsAscending() throws Exception {
		Mockito.when(hotelsInfoService.getHotelsNearLocation(Mockito.anyInt(), Mockito.any(), Mockito.anyBoolean())).thenReturn(createHotelsMock());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/location/1/hotels?sort_by=RATING").accept(
				MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
	}
	
	@Test
	public void testgetHotelsInfoFailureWhenMissingRequestParameterSortBy() throws Exception {
		Mockito.when(hotelsInfoService.getHotelsNearLocation(Mockito.anyInt(), Mockito.any(), Mockito.anyBoolean())).thenReturn(createHotelsMock());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/location/1/hotels?is_ascending=true").accept(
				MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
	}
	
	private List<Hotel> createHotelsMock() {
		List<Hotel> hotels = new ArrayList<>();
		return hotels;
	}

}
