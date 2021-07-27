package com.example.rac.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rac.MainActivity;
import com.example.rac.R;
import com.example.rac.models.Users;
import com.example.rac.viewModels.UsersViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    private UsersViewModel usersViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setSharedElementsUseOverlay(false);
        setContentView(R.layout.activity_login);

        usersViewModel = new UsersViewModel(this);

        TextInputLayout tilUsername = findViewById(R.id.oTF_username);
        TextInputLayout tilPassword = findViewById(R.id.oTF_password);

        //Sign-in button click event
        findViewById(R.id.bu_sign_in).setOnClickListener(v -> {

            String username = tilUsername.getEditText().getText().toString().trim();
            String password = tilPassword.getEditText().getText().toString().trim();

            if (username.isEmpty() || !username.contains("@") || !username.contains(".")) {
                tilUsername.setError("Enter correct Email address");
            } else if (password.isEmpty() || password.length() < 8) {
                if (tilUsername.isErrorEnabled()) {
                    tilUsername.setErrorEnabled(false);
                }
                tilPassword.setError("Enter correct password");
            } else {
                if (tilUsername.isErrorEnabled()) {
                    tilUsername.setErrorEnabled(false);
                }
                if (tilPassword.isErrorEnabled()) {
                    tilPassword.setErrorEnabled(false);
                }
                usersViewModel.loginUser(new Users(username, password)).observe(this, s -> {
                    if (s.equals(password)) {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class)
                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    } else {
                        Snackbar.make(findViewById(android.R.id.content), "Incorrect password", Snackbar.LENGTH_SHORT).show();
                    }
                });
            }

        });


        //Sign-up button click event
        findViewById(R.id.bu_sign_up).setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));
    }
}