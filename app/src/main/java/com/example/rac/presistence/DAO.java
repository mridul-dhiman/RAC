package com.example.rac.presistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.rac.models.TravelPlan;
import com.example.rac.models.Users;

@Dao
public interface DAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long[] insertUser(Users... users);

    @Query("SELECT Email FROM Users WHERE Email = :email AND Password = :password LIMIT 1")
    LiveData<String> loginStatus(String email, String password);

    @Query("SELECT Name FROM Users WHERE Email = :email LIMIT 1")
    LiveData<String> getUserName(String email);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertTravelPlan(TravelPlan... travelPlans);

    @Query("SELECT * FROM TravelPlan WHERE email = :email LIMIT 1")
    LiveData<TravelPlan> getTravelPlans(String email);
}
