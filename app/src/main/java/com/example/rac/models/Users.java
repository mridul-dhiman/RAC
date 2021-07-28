package com.example.rac.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Users")
public class Users {

    @NonNull
    @ColumnInfo(name = "Email")
    @PrimaryKey
    private final String email;

    @ColumnInfo(name = "Name")
    private String name;

    @NonNull
    @ColumnInfo(name = "Password")
    private final String password;

    public Users(@NonNull String email, @NonNull String name, @NonNull String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    @Ignore
    public Users(@NonNull String email, @NonNull String password) {
        this.email = email;
        this.password = password;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getPassword() {
        return password;
    }
}
