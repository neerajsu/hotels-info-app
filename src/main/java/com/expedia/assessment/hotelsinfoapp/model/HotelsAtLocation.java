package com.expedia.assessment.hotelsinfoapp.model;

import java.math.BigDecimal;
import java.util.List;

public class HotelsAtLocation {

	private Integer location_id;
	private String name;
	private BigDecimal location_x;
	private BigDecimal location_y;
	private BigDecimal location_z;
	private List<Tax> taxes;
	private List<Hotel> hotels;

	public HotelsAtLocation() {
		super();
	}

	public HotelsAtLocation(Integer location_id, String name, BigDecimal location_x, BigDecimal location_y,
			BigDecimal location_z, List<Tax> taxes, List<Hotel> hotels) {
		this.location_id = location_id;
		this.name = name;
		this.location_x = location_x;
		this.location_y = location_y;
		this.location_z = location_z;
		this.taxes = taxes;
		this.hotels = hotels;
	}

	public Integer getLocation_id() {
		return location_id;
	}

	public void setLocation_id(Integer location_id) {
		this.location_id = location_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getLocation_x() {
		return location_x;
	}

	public void setLocation_x(BigDecimal location_x) {
		this.location_x = location_x;
	}

	public BigDecimal getLocation_y() {
		return location_y;
	}

	public void setLocation_y(BigDecimal location_y) {
		this.location_y = location_y;
	}

	public BigDecimal getLocation_z() {
		return location_z;
	}

	public void setLocation_z(BigDecimal location_z) {
		this.location_z = location_z;
	}

	public List<Tax> getTaxes() {
		return taxes;
	}

	public void setTaxes(List<Tax> taxes) {
		this.taxes = taxes;
	}

	public List<Hotel> getHotels() {
		return hotels;
	}

	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}

	@Override
	public String toString() {
		return "CityDataModel{" + "location_id=" + location_id + ", name='" + name + '\'' + ", location_x=" + location_x
				+ ", location_y=" + location_y + ", location_z=" + location_z + ", taxes=" + taxes + ", hotels="
				+ hotels + '}';
	}
}
