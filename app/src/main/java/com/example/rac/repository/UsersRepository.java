package com.example.rac.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

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

    public void insertUser(Users users) {
        Users[] use = new Users[1];
        use[0] = users;
        new Thread(() -> {
            long[] l = mainDatabase.getDAO().insertUser(use);
            for (long lo : l) {
                Log.d(TAG, "run: " + lo);
            }
        }).start();
    }

    public LiveData<String> loginUser(Users users) {
        return mainDatabase.getDAO().loginStatus(users.getEmail(), users.getPassword());
    }

}
