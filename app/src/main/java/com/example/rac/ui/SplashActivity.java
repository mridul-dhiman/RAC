package com.example.rac.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rac.R;

import static com.example.rac.utils.Constants.SHARED_PREFS;
import static com.example.rac.utils.Constants.SHARED_PREFS_USER_EMAIL;

public class SplashActivity extends AppCompatActivity {

    private final Handler handler = new Handler(Looper.myLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Decide next activity
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String email = sharedPreferences.getString(SHARED_PREFS_USER_EMAIL, null);

        Intent intent;
        if (email != null && !email.isEmpty()) {
            intent = new Intent(SplashActivity.this, MainActivity.class);
        } else {
            intent = new Intent(SplashActivity.this, LoginActivity.class);
        }

        //Go to next activity
        handler.postDelayed(() -> startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK)), 7000);
    }
}