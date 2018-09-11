package com.expedia.assessment.hotelsinfoapp.util;

import java.util.Comparator;
import java.util.List;

import com.expedia.assessment.hotelsinfoapp.model.Hotel;
import com.expedia.assessment.hotelsinfoapp.model.UserRating;

public class HotelRatingComparator implements Comparator<Hotel> {

	private boolean isAscending;

	public HotelRatingComparator(boolean isAscending) {
		this.isAscending = isAscending;
	}

	@Override
	public int compare(Hotel hotel1, Hotel hotel2) {
		if (isAscending) {
			return Float.compare(getAverageRatingFromRatings(hotel1.getUser_ratings()),
					getAverageRatingFromRatings(hotel2.getUser_ratings()));
		} else {
			return Float.compare(getAverageRatingFromRatings(hotel2.getUser_ratings()),
					getAverageRatingFromRatings(hotel1.getUser_ratings()));
		}
	}

	private float getAverageRatingFromRatings(List<UserRating> ratings) {
		float rating = 0f;
		float sum = 0;
		if (ratings.size() == 0) {
			return rating;
		}
		for (UserRating userRating : ratings) {
			sum = sum + userRating.getRating();
		}
		rating = sum / ratings.size();
		return rating;
	}
}