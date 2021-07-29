package com.example.rac.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rac.R;
import com.example.rac.adapter.RecyclerViewAdapter;
import com.example.rac.adapter.RecyclerViewClickListener;
import com.example.rac.models.Cars;
import com.example.rac.models.TravelPlan;
import com.example.rac.utils.CreateCarsList;
import com.example.rac.viewModels.UsersViewModel;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import static com.example.rac.utils.Constants.SHARED_PREFS;
import static com.example.rac.utils.Constants.SHARED_PREFS_USER_EMAIL;

public class MainActivity extends AppCompatActivity implements RecyclerViewClickListener {
    private static final String TAG = "MainActivity";

    private List<Cars> carsList = new ArrayList<>();
    private TextView textViewUserWelcomeMessage;
    private Button btnDatePicker, btnConfirm;
    private TextInputLayout tilTravelDays;
    private RecyclerViewAdapter adapter;
    private String selectedDate;
    private int selectedDuration;
    private int selectedCarPosition = -1;
    private MaterialDatePicker<?> materialDatePicker;

    private String email;
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
            if (selectedDate != null && selectedDuration > 0 && selectedCarPosition != -1) {
                usersViewModel.saveTravelPlan(new TravelPlan(email, selectedDate, selectedDuration, selectedCarPosition));
                startActivity(new Intent(MainActivity.this, FinalActivity.class));
            }
        });

    }

    private void initViews() {
        textViewUserWelcomeMessage = findViewById(R.id.tV_welcome_back_message);
        btnDatePicker = findViewById(R.id.bu_date_selector);
        btnConfirm = findViewById(R.id.bu_confirm);
        tilTravelDays = findViewById(R.id.oTF_travel_duration);
        RecyclerView rvCarsList = findViewById(R.id.rv_car_selector);

        carsList = CreateCarsList.getInstance().getCarsList();
        adapter = new RecyclerViewAdapter(carsList, this);

        rvCarsList.setHasFixedSize(true);
        rvCarsList.setAdapter(adapter);

        usersViewModel = new UsersViewModel(this);

        tilTravelDays.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    rvCarsList.setVisibility(View.VISIBLE);
                    if (selectedCarPosition != -1) {
                        btnConfirm.setVisibility(View.VISIBLE);
                    }
                } else {
                    rvCarsList.setVisibility(View.GONE);
                    if (btnConfirm.getVisibility() == View.VISIBLE) {
                        btnConfirm.setVisibility(View.GONE);
                    }
                }
                selectedDuration = Integer.parseInt(s.toString());
                Log.d(TAG, "onTextChanged: rvVisi " + carsList.size());
                Log.d(TAG, "onTextChanged: rvVisi " + (rvCarsList.getVisibility() == View.VISIBLE));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        setUserName();
    }

    private void setUserName() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        email = sharedPreferences.getString(SHARED_PREFS_USER_EMAIL, null);

        usersViewModel.getUserName(email).observe(this, s -> {
            if (s != null && !s.isEmpty()) {
                textViewUserWelcomeMessage.setText(String.format(getString(R.string.str_home_user_head), s));
            }
        });
    }

    private void createDatePicker() {
        MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
        builder.setSelection(MaterialDatePicker.todayInUtcMilliseconds());
        builder.setTitleText("Select start date");
        materialDatePicker = builder.build();
        materialDatePicker.addOnPositiveButtonClickListener(selection -> {
            selectedDate = materialDatePicker.getHeaderText();
            btnDatePicker.setText(selectedDate);
            tilTravelDays.setVisibility(View.VISIBLE);
        });
    }


    @Override
    public void OnItemClick(int position) {
        Log.d(TAG, "OnItemClick: position " + position);
        if (selectedCarPosition != -1 && selectedCarPosition != position) {
            Cars car = carsList.get(selectedCarPosition);
            car.setSelected(false);
        }
        if (carsList.get(position).isAvailable()) {
            selectedCarPosition = position;
            Cars car = carsList.get(position);
            car.setSelected(true);
            carsList.set(position, car);
            adapter.notifyDataSetChanged();
            btnConfirm.setVisibility(View.VISIBLE);
        }
    }
}