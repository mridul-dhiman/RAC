package com.example.rac.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import com.example.rac.R;

public class SplashActivity extends AppCompatActivity {

    private final Handler handler = new Handler(Looper.myLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Prepare animation
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        //Start animation
        findViewById(R.id.iV_app_logo).startAnimation(animation);

        //Enable view after animation ends
        ImageView logoBig = findViewById(R.id.iV_app_logo_big);
        handler.postDelayed(() -> logoBig.setVisibility(View.VISIBLE), 1000);

        //Prepare bundle
        ActivityOptionsCompat compat = ActivityOptionsCompat
                .makeSceneTransitionAnimation(SplashActivity.this,
                        logoBig, logoBig.getTransitionName());

        //Go to next activity
        handler.postDelayed(() -> startActivity(new Intent(SplashActivity.this, LoginActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK), compat.toBundle()),
                1500);

    }
}