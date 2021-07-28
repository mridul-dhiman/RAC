package com.example.rac.utils;

import com.example.rac.models.Cars;
import java.util.ArrayList;
import java.util.List;

public class CreateCarsList {

    private static CreateCarsList instance;

    private final List<Cars> carsList = new ArrayList<>();

    private CreateCarsList() {
        carsList.add(new Cars("Hyundai elite i20","4+1 seater","2017-2018","Petrol"));
        carsList.add(new Cars("Renault Kwid","4+1 seater","2020","Diesel"));
        carsList.add(new Cars("Hyundai Creta","4+1 seater","2019","Diesel"));
        carsList.add(new Cars("Mahindra eXUV300","6+1 seater","2021","Electric"));
        carsList.add(new Cars("Skoda Rapid","4+1 seater","2017","Petrol"));
    }

    public static CreateCarsList getInstance() {
        if (instance == null) {
            instance = new CreateCarsList();
        }
        return instance;
    }

    public List<Cars> getCarsList() {
        return carsList;
    }
}