package com.expedia.assessment.hotelsinfoapp.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expedia.assessment.hotelsinfoapp.constants.SortOptionEnum;
import com.expedia.assessment.hotelsinfoapp.exception.ServiceException;
import com.expedia.assessment.hotelsinfoapp.model.Coordinate;
import com.expedia.assessment.hotelsinfoapp.model.Hotel;
import com.expedia.assessment.hotelsinfoapp.model.HotelsAtLocation;
import com.expedia.assessment.hotelsinfoapp.util.HotelRatingComparator;
import com.expedia.assessment.hotelsinfoapp.util.HotelsDistanceComparator;
import com.expedia.assessment.hotelsinfoapp.util.HotelsPriceComparator;
import com.expedia.library.sort.SortingUtil;

/*Note:- 
I have added multiple sort algorithms in a library which are being used here.
I would never actually use bubble sort in real situation.
I don't see any use case to use this unless the list is already fairly sorted.
For most use cases, default Java sorting should work. Insertion sort and quick sort is okay.
Seems like Java uses merge sort so didn't implement
I've provided an example using stream api for name sorting where default java sort is used*/
@Service
public class HotelsInfoServiceImpl implements HotelsInfoService {

	@Autowired
	LocationsWithHotelsDataLoader locationsWithHotelsDataLoader;

	@Override
	public List<Hotel> getHotelsNearLocation(Integer locationId, SortOptionEnum sortParameter, boolean isAscending)
			throws ServiceException {
		List<Hotel> hotels = new ArrayList<>();
		Optional<HotelsAtLocation> hotelsAtLocationOptional = null;
		try {
			hotelsAtLocationOptional = locationsWithHotelsDataLoader.getAllHotelsForALocation(locationId);
		} catch (IOException e) {
			throw new ServiceException("Exception Occured while trying to load data from json", e);
		}
		if (hotelsAtLocationOptional.isPresent()) {
			HotelsAtLocation hotelsAtLocation = hotelsAtLocationOptional.get();
			switch (sortParameter) {
			case PRICE:
				SortingUtil.bubbleSort(hotelsAtLocation.getHotels(), new HotelsPriceComparator(isAscending));
				hotels = hotelsAtLocation.getHotels();
				break;
			case DISTANCE:
				Coordinate baseLocationCoordinate = new Coordinate(hotelsAtLocation.getLocation_x(),
						hotelsAtLocation.getLocation_y(), hotelsAtLocation.getLocation_z());
				SortingUtil.insertionSort(hotelsAtLocation.getHotels(),
						new HotelsDistanceComparator(baseLocationCoordinate, isAscending));
				hotels = hotelsAtLocation.getHotels();
				break;
			case NAME:
				if(isAscending) {
					hotels = hotelsAtLocation.getHotels().stream().sorted((hotel1, hotel2) -> hotel1.getName().compareToIgnoreCase(hotel2.getName())).collect(Collectors.toList());
				} else {
					hotels = hotelsAtLocation.getHotels().stream().sorted((hotel1, hotel2) -> hotel2.getName().compareToIgnoreCase(hotel1.getName())).collect(Collectors.toList());
				}
				break;
			case RATING:
				SortingUtil.quickSort(hotelsAtLocation.getHotels(), new HotelRatingComparator(isAscending));
				hotels = hotelsAtLocation.getHotels();
				break;
			default:
				hotels = hotelsAtLocation.getHotels();
				break;
			}
			
		}

		return hotels;
	}

}
