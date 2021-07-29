package com.example.rac.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.rac.models.TravelPlan;
import com.example.rac.models.Users;
import com.example.rac.presistence.MainDatabase;

import java.util.List;

public class UsersRepository {
    private static final String TAG = "UsersRepository";

    private final MainDatabase mainDatabase;

    private static UsersRepository instance;

    private UsersRepository(Context context) {
        mainDatabase = MainDatabase.getInstance(context);
    }

    public static UsersRepository getInstance(Context context) {
        if (instance == null) {
            instance = new UsersRepository(context);
        }
        return instance;
    }

    public long insertUser(Users users) {
        Users[] use = new Users[1];
        use[0] = users;
        final long[][] l = {new long[1]};
        new Thread(() -> {
            l[0] = mainDatabase.getDAO().insertUser(use);
            for (long lo : l[0]) {
                Log.d(TAG, "run: User " + lo);
            }
        }).start();
        return l[0][0];
    }

    public LiveData<String> loginUser(Users users) {
        return mainDatabase.getDAO().loginStatus(users.getEmail(), users.getPassword());
    }

    public LiveData<String> getUserName(String email) {
        return mainDatabase.getDAO().getUserName(email);
    }

    public void saveTravelPlan(TravelPlan travelPlan) {
        TravelPlan[] travelPlans = new TravelPlan[1];
        travelPlans[0] = travelPlan;
        new Thread(() -> {
            long[] l = mainDatabase.getDAO().insertTravelPlan(travelPlans);
            for (long lo : l) {
                Log.d(TAG, "run: TP " + lo);
            }
        }).start();
    }

    public LiveData<List<TravelPlan>> getTravelPlans(String email) {
        return mainDatabase.getDAO().getTravelPlans(email);
    }

}
