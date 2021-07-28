package com.example.rac.models;

public class Cars {

    private final String carName;
    private final String carSeats;
    private final String carReleaseYear;
    private final String carFuelType;
    private boolean available = false;
    private boolean selected = false;

    public Cars(String carName, String carSeats, String carReleaseYear, String carFuelType) {
        this.carName = carName;
        this.carSeats = carSeats;
        this.carReleaseYear = carReleaseYear;
        this.carFuelType = carFuelType;
    }

    public String getCarName() {
        return carName;
    }

    public String getCarSeats() {
        return carSeats;
    }

    public String getCarReleaseYear() {
        return carReleaseYear;
    }

    public String getCarFuelType() {
        return carFuelType;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}