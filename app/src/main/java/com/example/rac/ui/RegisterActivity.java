package com.example.rac.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rac.R;
import com.example.rac.models.Users;
import com.example.rac.viewModels.UsersViewModel;
import com.google.android.material.textfield.TextInputLayout;

import static com.example.rac.utils.Constants.SHARED_PREFS;
import static com.example.rac.utils.Constants.SHARED_PREFS_USER_EMAIL;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";

    private UsersViewModel usersViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextInputLayout tilName = findViewById(R.id.oTF_name);
        TextInputLayout tilEmail = findViewById(R.id.oTF_email);
        TextInputLayout tilPassword = findViewById(R.id.oTF_password);

        usersViewModel = new UsersViewModel(this);

        //Sign-in button click event
        findViewById(R.id.bu_sign_up).setOnClickListener(v -> {

            String name = tilName.getEditText().getText().toString().trim();
            String email = tilEmail.getEditText().getText().toString().trim();
            String password = tilPassword.getEditText().getText().toString().trim();

            if (name.isEmpty()) {
                tilEmail.setError("Enter correct Name");
            } else if (email.isEmpty() || !email.contains("@") || !email.contains(".")) {
                if (tilName.isErrorEnabled()) {
                    tilName.setErrorEnabled(false);
                }
                tilEmail.setError("Enter correct Email address");
            } else if (password.isEmpty() || password.length() < 8) {
                if (tilName.isErrorEnabled()) {
                    tilName.setErrorEnabled(false);
                }
                if (tilEmail.isErrorEnabled()) {
                    tilEmail.setErrorEnabled(false);
                }
                tilPassword.setError("Enter correct password");
            } else {
                if (tilName.isErrorEnabled()) {
                    tilName.setErrorEnabled(false);
                }
                if (tilEmail.isErrorEnabled()) {
                    tilEmail.setErrorEnabled(false);
                }
                if (tilPassword.isErrorEnabled()) {
                    tilPassword.setErrorEnabled(false);
                }
                long result = usersViewModel.insertUser(new Users(email, name, password));
                if (result != -1) {
                    //Save user login
                    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(SHARED_PREFS_USER_EMAIL, email);
                    editor.apply();

                    startActivity(new Intent(RegisterActivity.this, MainActivity.class)
                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                }
                Log.d(TAG, "onCreate: register");
            }

        });
    }
}