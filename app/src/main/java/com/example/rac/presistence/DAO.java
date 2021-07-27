package com.example.rac.presistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.rac.models.Users;

@Dao
public interface DAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long[] insertUser(Users... users);

    @Query("SELECT password FROM Users WHERE userName = :userName")
    LiveData<String> loginStatus(String userName);
}
