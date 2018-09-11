package com.expedia.assessment.hotelsinfoapp.model;

import java.math.BigDecimal;

public class Tax {

	private String name;
	private BigDecimal rate;

	public Tax() {
		super();
	}

	public Tax(String name, BigDecimal rate) {
		this.name = name;
		this.rate = rate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "TaxDataModel{" + "name='" + name + '\'' + ", rate=" + rate + '}';
	}

}
