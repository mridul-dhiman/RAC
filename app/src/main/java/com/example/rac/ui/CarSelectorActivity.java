package com.example.rac.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rac.R;
import com.example.rac.adapter.RecyclerViewAdapter;
import com.example.rac.adapter.RecyclerViewClickListener;
import com.example.rac.models.Cars;
import com.example.rac.models.TravelPlan;
import com.example.rac.utils.CreateCarsList;
import com.example.rac.viewModels.UsersViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import static com.example.rac.utils.Constants.SHARED_PREFS;
import static com.example.rac.utils.Constants.SHARED_PREFS_USER_EMAIL;

public class CarSelectorActivity extends AppCompatActivity implements RecyclerViewClickListener {
    private static final String TAG = "CarSelectorActivity";

    private List<Cars> carsList = new ArrayList<>();

    private RecyclerViewAdapter adapter;
    private UsersViewModel usersViewModel;

    private int selectedCarPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_selector);

        RecyclerView rvCarsList = findViewById(R.id.rv_car_selector);

        usersViewModel = new UsersViewModel(this);

        carsList = CreateCarsList.getInstance().getCarsList();
        adapter = new RecyclerViewAdapter(carsList, this);

        rvCarsList.setHasFixedSize(true);
        rvCarsList.setAdapter(adapter);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String email = sharedPreferences.getString(SHARED_PREFS_USER_EMAIL, null);

        Intent intent = getIntent();
        String selectedDate = intent.getStringExtra("DATE");

        findViewById(R.id.bu_confirm).setOnClickListener(v -> {
            if (selectedDate != null && selectedCarPosition != -1) {
                usersViewModel.saveTravelPlan(new TravelPlan(email, selectedDate, selectedCarPosition));
                startActivity(new Intent(CarSelectorActivity.this, FinalActivity.class));
            } else {
                Snackbar.make(findViewById(android.R.id.content), "Select a car first", Snackbar.LENGTH_SHORT).show();
            }
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
        }
    }
}