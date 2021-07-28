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

    private UsersViewModel usersViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setSharedElementsUseOverlay(false);
        setContentView(R.layout.activity_login);

        usersViewModel = new UsersViewModel(this);

        TextInputLayout tilEmail = findViewById(R.id.oTF_email);
        TextInputLayout tilPassword = findViewById(R.id.oTF_password);

        //Sign-in button click event
        findViewById(R.id.bu_sign_in).setOnClickListener(v -> {

            String username = tilEmail.getEditText().getText().toString().trim();
            String password = tilPassword.getEditText().getText().toString().trim();

            if (username.isEmpty() || !username.contains("@") || !username.contains(".")) {
                tilEmail.setError("Enter correct Email address");
            } else if (password.isEmpty() || password.length() < 8) {
                if (tilEmail.isErrorEnabled()) {
                    tilEmail.setErrorEnabled(false);
                }
                tilPassword.setError("Enter correct password");
            } else {
                if (tilEmail.isErrorEnabled()) {
                    tilEmail.setErrorEnabled(false);
                }
                if (tilPassword.isErrorEnabled()) {
                    tilPassword.setErrorEnabled(false);
                }
                usersViewModel.loginUser(new Users(username, password)).observe(this, s -> {
                    //Log.d("LoginActivity", "onCreate: " + s.size());
                    if (s != null && s.equals(username)) {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class)
                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    } else {
                        Snackbar.make(findViewById(android.R.id.content), "Incorrect email or password", Snackbar.LENGTH_SHORT).show();
                    }
                });
            }

        });


        //Sign-up button click event
        findViewById(R.id.bu_sign_up).setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));
    }
}