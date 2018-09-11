package com.expedia.assessment.hotelsinfoapp.util;

import java.math.BigDecimal;
import java.util.Comparator;

import com.expedia.assessment.hotelsinfoapp.model.Coordinate;
import com.expedia.assessment.hotelsinfoapp.model.Hotel;

public class HotelsDistanceComparator implements Comparator<Hotel> {

	private Coordinate baseLocation;
	private boolean isAscending;

	public HotelsDistanceComparator(Coordinate baseLocation, boolean isAscending) {
		this.isAscending = isAscending;
		this.baseLocation = baseLocation;
	}

	@Override
	public int compare(Hotel hotel1, Hotel hotel2) {
		Coordinate location1 = new Coordinate(hotel1.getLocation_x(), hotel1.getLocation_y(), hotel1.getLocation_z());
		Coordinate location2 = new Coordinate(hotel2.getLocation_x(), hotel2.getLocation_y(), hotel2.getLocation_z());
		if(isAscending) {
			return distanceBetweenLocations(baseLocation, location1).compareTo(distanceBetweenLocations(baseLocation, location2));
		} else {
			return distanceBetweenLocations(baseLocation, location2).compareTo(distanceBetweenLocations(baseLocation, location1));
		}
		
	}

	private BigDecimal distanceBetweenLocations(Coordinate coordinate1, Coordinate coordinate2) {
		BigDecimal distance = new BigDecimal(0);
		distance = new BigDecimal(Math.sqrt((coordinate1.getLocation_x().subtract(coordinate2.getLocation_x())).pow(2)
				.add((coordinate1.getLocation_y().subtract(coordinate2.getLocation_y())).pow(2))
				.add((coordinate1.getLocation_z().subtract(coordinate2.getLocation_z())).pow(2)).doubleValue()));
		return distance;
	}
}
