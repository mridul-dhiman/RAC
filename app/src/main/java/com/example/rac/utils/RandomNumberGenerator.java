package com.example.rac.utils;

import java.util.Random;

public class RandomNumberGenerator {

    private static RandomNumberGenerator instance;
    private final Random random;

    private RandomNumberGenerator() {
        random = new Random();
    }

    public static RandomNumberGenerator getInstance() {
        if (instance == null) {
            instance = new RandomNumberGenerator();
        }
        return instance;
    }

    public int getRandomNumber() {
        return random.nextInt(5);
    }
}
