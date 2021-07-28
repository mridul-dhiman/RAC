package com.example.rac.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rac.R;
import com.example.rac.viewModels.UsersViewModel;

import static com.example.rac.utils.Constants.SHARED_PREFS;
import static com.example.rac.utils.Constants.SHARED_PREFS_USER_EMAIL;

public class FinalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        TextView tvMessage = findViewById(R.id.tV_final_text);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String email = sharedPreferences.getString(SHARED_PREFS_USER_EMAIL, "The one who shouldn't be named");

        UsersViewModel usersViewModel = new UsersViewModel(this);

        usersViewModel.getUserName(email).observe(this, s -> {
            if (s != null && !s.isEmpty()) {
                tvMessage.setText(String.format(getResources().getString(R.string.str_final_message), s));
            }
        });
    }
}