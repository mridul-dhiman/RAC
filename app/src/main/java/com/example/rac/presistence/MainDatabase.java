package com.example.rac.presistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.rac.models.TravelPlan;
import com.example.rac.models.Users;

@Database(entities = {Users.class, TravelPlan.class}, version = 1)
public abstract class MainDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "RAC_DATABASE";

    private static MainDatabase instance;

    public static MainDatabase getInstance(final Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), MainDatabase.class, DATABASE_NAME).build();
        }
        return instance;
    }

    public abstract DAO getDAO();

}
