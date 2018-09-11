package com.expedia.assessment.hotelsinfoapp.service;

import java.util.List;

import com.expedia.assessment.hotelsinfoapp.constants.SortOptionEnum;
import com.expedia.assessment.hotelsinfoapp.exception.ServiceException;
import com.expedia.assessment.hotelsinfoapp.model.Hotel;

public interface HotelsInfoService {
	List<Hotel> getHotelsNearLocation(Integer locationId, SortOptionEnum sortParameter, boolean isAscending) throws ServiceException;
}
