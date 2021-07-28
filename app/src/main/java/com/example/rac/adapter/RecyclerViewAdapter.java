package com.example.rac.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rac.R;
import com.example.rac.models.Cars;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CarsViewHolder> {

    private final List<Cars> carsList;

    public RecyclerViewAdapter(List<Cars> carsList) {
        this.carsList = carsList;
    }

    @NonNull
    @Override
    public CarsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cars_list_layout, parent, false);
        return new CarsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.CarsViewHolder holder, int position) {
        holder.onBind(carsList.get(position));
    }

    @Override
    public int getItemCount() {
        return carsList.size();
    }

    protected static class CarsViewHolder extends RecyclerView.ViewHolder {
        private final TextView carName, carSeats, carReleaseYear, carFuelType;

        public CarsViewHolder(@NonNull View itemView) {
            super(itemView);

            carName = itemView.findViewById(R.id.tv_car_name);
            carSeats = itemView.findViewById(R.id.tv_car_seats);
            carReleaseYear = itemView.findViewById(R.id.tv_car_release_year);
            carFuelType = itemView.findViewById(R.id.tv_car_fuel_type);

        }

        public void onBind(Cars car) {
            carName.setText(car.getCarName());
            carSeats.setText(car.getCarSeats());
            carReleaseYear.setText(car.getCarReleaseYear());
            carFuelType.setText(car.getCarFuelType());
        }
    }
}
