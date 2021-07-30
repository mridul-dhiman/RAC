package com.example.rac.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "TravelPlan")
public class TravelPlan {

    @PrimaryKey
    @NonNull
    private final String email;
    private final String date;
    private final int carSelected;
    private String adhaar;
    private String license;

    public TravelPlan(@NonNull String email, String date, int carSelected, String adhaar, String license) {
        this.email = email;
        this.date = date;
        this.carSelected = carSelected;
        this.adhaar = adhaar;
        this.license = license;
    }

    public String getEmail() {
        return email;
    }

    public String getDate() {
        return date;
    }

    public int getCarSelected() {
        return carSelected;
    }

    public String getAdhaar() {
        return adhaar;
    }

    public String getLicense() {
        return license;
    }
}
