package com.expedia.assessment.hotelsinfoapp.model;

import java.math.BigDecimal;

public class Coordinate {
    public Coordinate(BigDecimal location_x, BigDecimal location_y, BigDecimal location_z) {
        this.location_x = location_x;
        this.location_y = location_y;
        this.location_z = location_z;
    }

    private BigDecimal location_x;
    private BigDecimal location_y;
    private BigDecimal location_z;

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

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + location_x +
                ", y=" + location_y +
                ", z=" + location_z +
                '}';
    }

}
