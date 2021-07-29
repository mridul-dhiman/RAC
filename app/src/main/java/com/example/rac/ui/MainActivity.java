package com.example.rac.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import com.example.rac.R;
import com.example.rac.viewModels.UsersViewModel;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.snackbar.Snackbar;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.TimeZone;

import static com.example.rac.utils.Constants.SHARED_PREFS;
import static com.example.rac.utils.Constants.SHARED_PREFS_USER_EMAIL;

public class MainActivity extends AppCompatActivity {

    private TextView textViewUserWelcomeMessage;
    private Button btnDatePicker, btnConfirm;
    private String selectedDate;
    private MaterialDatePicker<?> materialDatePicker;
    private SharedPreferences sharedPreferences;

    private UsersViewModel usersViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        createDatePicker();

        btnDatePicker.setOnClickListener(v ->
                materialDatePicker.show(getSupportFragmentManager(), "DatePicker"));

        btnConfirm.setOnClickListener(v -> {
            if (selectedDate != null) {
                startActivity(new Intent(MainActivity.this, CarSelectorActivity.class).putExtra("DATE", selectedDate));
            } else {
                Snackbar.make(findViewById(android.R.id.content), "Select dates first", Snackbar.LENGTH_SHORT).show();
            }
        });

    }

    private void initViews() {
        textViewUserWelcomeMessage = findViewById(R.id.tV_welcome_back_message);
        btnDatePicker = findViewById(R.id.bu_date_selector);
        btnConfirm = findViewById(R.id.bu_confirm);

        usersViewModel = new UsersViewModel(this);

        setUserName();
    }

    private void setUserName() {
        sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String email = sharedPreferences.getString(SHARED_PREFS_USER_EMAIL, null);

        usersViewModel.getUserName(email).observe(this, s -> {
            if (s != null && !s.isEmpty()) {
                textViewUserWelcomeMessage.setText(String.format(getString(R.string.str_home_user_head), s));
            }
        });
    }

    private void createDatePicker() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
        long start = calendar.getTimeInMillis();
        calendar.set(Calendar.YEAR, LocalDate.MAX.getYear());
        long end = calendar.getTimeInMillis();

        CalendarConstraints.Builder ccBuilder = new CalendarConstraints.Builder();
        ccBuilder.setStart(start);
        ccBuilder.setEnd(end);

        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        builder.setCalendarConstraints(ccBuilder.build());
        builder.setTitleText("Select start date");
        materialDatePicker = builder.build();
        materialDatePicker.addOnPositiveButtonClickListener(selection -> {
            selectedDate = materialDatePicker.getHeaderText();
            btnDatePicker.setText(selectedDate);
            btnConfirm.setVisibility(View.VISIBLE);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_logout) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(SHARED_PREFS_USER_EMAIL);
            editor.apply();
            startActivity(new Intent(MainActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}