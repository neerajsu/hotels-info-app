package com.expedia.assessment.hotelsinfoapp.model;

public class UserRating {

	private String user_id;
	private Integer rating;

	public UserRating() {
		super();
	}

	public UserRating(String user_id, Integer rating) {
		this.user_id = user_id;
		this.rating = rating;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "UserRatingDataModel{" + "user_id='" + user_id + '\'' + ", rating=" + rating + '}';
	}
}
