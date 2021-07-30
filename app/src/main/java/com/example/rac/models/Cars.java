package com.example.rac.models;

public class Cars {

    private final int carImage;
    private final String carName;
    private final String carSeats;
    private final String carReleaseYear;
    private final String carFuelType;
    private boolean available;
    private boolean selected;

    public Cars(int carImage, String carName, String carSeats, String carReleaseYear, String carFuelType) {
        this.carImage = carImage;
        this.carName = carName;
        this.carSeats = carSeats;
        this.carReleaseYear = carReleaseYear;
        this.carFuelType = carFuelType;
        this.available = true;
        this.selected = false;
    }

    public int getCarImage() {
        return carImage;
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