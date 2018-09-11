package com.expedia.assessment.hotelsinfoapp.exception;

public class ServiceException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ServiceException(String s, Throwable t) {
		super(s, t);
	}
}
