package com.example.rac.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "TravelPlan")
public class TravelPlan {

    @PrimaryKey
    @NonNull
    private final String email;
    private final String startDate;
    private final int travelDays;
    private final int carSelected;

    public TravelPlan(@NonNull String email, String startDate, int travelDays, int carSelected) {
        this.email = email;
        this.startDate = startDate;
        this.travelDays = travelDays;
        this.carSelected = carSelected;
    }

    public String getEmail() {
        return email;
    }

    public String getStartDate() {
        return startDate;
    }

    public int getTravelDays() {
        return travelDays;
    }

    public int getCarSelected() {
        return carSelected;
    }
}
