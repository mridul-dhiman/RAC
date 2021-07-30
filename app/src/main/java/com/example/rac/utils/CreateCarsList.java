package com.example.rac.utils;

import android.util.Log;

import com.example.rac.R;
import com.example.rac.models.Cars;

import java.util.ArrayList;
import java.util.List;

public class CreateCarsList {
    private static final String TAG = "CreateCarsList";

    private static CreateCarsList instance;

    private final List<Cars> carsList = new ArrayList<>();

    int unavailableCarsCount = 0;

    private CreateCarsList() {
        carsList.add(new Cars(R.drawable.i20, "Hyundai elite i20", "4+1 seater", "2017-2018", "Petrol"));
        carsList.add(new Cars(R.drawable.kwid, "Renault Kwid", "4+1 seater", "2020", "Diesel"));
        carsList.add(new Cars(R.drawable.creta, "Hyundai Creta", "4+1 seater", "2019", "Diesel"));
        carsList.add(new Cars(R.drawable.mahindra_xuv, "Mahindra eXUV300", "6+1 seater", "2021", "Electric"));
        carsList.add(new Cars(R.drawable.skoda_rapid, "Skoda Rapid", "4+1 seater", "2017", "Petrol"));

        setUnavailableCars();
    }

    public static CreateCarsList getInstance() {
        if (instance == null) {
            instance = new CreateCarsList();
        }
        return instance;
    }

    private void setUnavailableCars() {
        while (unavailableCarsCount < 2) {
            int index = RandomNumberGenerator.getInstance().getRandomNumber();
            Log.d(TAG, "setUnavailableCars: generated " + index);
            boolean exists = !carsList.get(index).isAvailable();
            Log.d(TAG, "setUnavailableCars: exists " + exists + " available " + carsList.get(index).isAvailable());
            if (exists) {
                setUnavailableCars();
            } else {
                Log.d(TAG, "setUnavailableCars: called");
                Cars updatedCar = carsList.get(index);
                updatedCar.setAvailable(false);
                carsList.set(index, updatedCar);
                unavailableCarsCount++;
            }
        }
    }

    public List<Cars> getCarsList() {
        return carsList;
    }
}