package com.example.rac.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rac.R;
import com.example.rac.models.TravelPlan;
import com.example.rac.viewModels.UsersViewModel;
import com.google.android.material.textfield.TextInputLayout;

import static com.example.rac.utils.Constants.SHARED_PREFS;
import static com.example.rac.utils.Constants.SHARED_PREFS_USER_EMAIL;

public class PersonalDetailsActivity extends AppCompatActivity {

    private UsersViewModel usersViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        TextInputLayout tilAdhaar = findViewById(R.id.oTF_adhaar_number);
        TextInputLayout tilDrivingLic = findViewById(R.id.oTF_licence);

        Intent intent = getIntent();
        String dates = intent.getStringExtra("DATE");
        int car = intent.getIntExtra("CAR", -1);

        usersViewModel = new UsersViewModel(this);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String email = sharedPreferences.getString(SHARED_PREFS_USER_EMAIL, null);

        findViewById(R.id.bu_confirm).setOnClickListener(v -> {
            String adhaar = tilAdhaar.getEditText().getText().toString().trim();
            String licence = tilDrivingLic.getEditText().getText().toString().trim();

            if (adhaar.length() != 12) {
                tilAdhaar.setError("Enter valid Adhaar number");
            } else if (licence.length() != 15) {
                if (tilAdhaar.isErrorEnabled()) {
                    tilAdhaar.setErrorEnabled(false);
                }
                tilDrivingLic.setError("Enter valid Driving licence");
            } else {
                if (tilAdhaar.isErrorEnabled()) {
                    tilAdhaar.setErrorEnabled(false);
                }
                if (tilDrivingLic.isErrorEnabled()) {
                    tilDrivingLic.setErrorEnabled(false);
                }

                usersViewModel.saveTravelPlan(new TravelPlan(email, dates, car, adhaar, licence));

                startActivity(new Intent(PersonalDetailsActivity.this, FinalActivity.class));
            }
        });
    }
}