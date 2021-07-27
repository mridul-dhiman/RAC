package com.example.rac.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Users")
public class Users {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @NonNull
    @ColumnInfo(name = "Name")
    private String name;

    @NonNull
    @ColumnInfo(name = "UserName")
    private final String userName;

    @NonNull
    @ColumnInfo(name = "Password")
    private final String password;

    public Users(@NonNull String name, @NonNull String userName, @NonNull String password) {
        this.name = name;
        this.userName = userName;
        this.password = password;
    }

    @Ignore
    public Users(@NonNull String userName, @NonNull String password) {
        this.userName = userName;
        this.password = password;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getUserName() {
        return userName;
    }

    @NonNull
    public String getPassword() {
        return password;
    }
}
