package com.expedia.assessment.hotelsinfoapp.util;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

import com.expedia.assessment.hotelsinfoapp.model.Hotel;
import com.expedia.assessment.hotelsinfoapp.model.Rate;
import com.expedia.library.sort.SortingUtil;

public class HotelsPriceComparator implements Comparator<Hotel> {

	private boolean isAscending;

	public HotelsPriceComparator(boolean isAscending) {
		this.isAscending = isAscending;
	}

	@Override
	public int compare(Hotel hotel1, Hotel hotel2) {
		if (this.isAscending) {
			return getLowestPriceFromPrices(hotel1.getRates()).subtract(getLowestPriceFromPrices(hotel2.getRates()))
					.intValue();
		} else {
			return getLowestPriceFromPrices(hotel2.getRates()).subtract(getLowestPriceFromPrices(hotel1.getRates()))
					.intValue();
		}
	}

	private BigDecimal getLowestPriceFromPrices(List<Rate> rates) {
		return SortingUtil.findMinValue(rates).getBase_price();
	}

}
