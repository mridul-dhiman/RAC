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

    private final RecyclerViewClickListener viewClickListener;

    public RecyclerViewAdapter(List<Cars> carsList, RecyclerViewClickListener viewClickListener) {
        this.carsList = carsList;
        this.viewClickListener = viewClickListener;
    }

    @NonNull
    @Override
    public CarsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cars_list_layout, parent, false);
        return new CarsViewHolder(view, viewClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.CarsViewHolder holder, int position) {
        holder.onBind(carsList.get(position));
    }

    @Override
    public int getItemCount() {
        return carsList.size();
    }

    protected static class CarsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView carName, carSeats, carReleaseYear, carFuelType;
        private final RecyclerViewClickListener viewClickListener;

        public CarsViewHolder(@NonNull View itemView, RecyclerViewClickListener clickListener) {
            super(itemView);

            carName = itemView.findViewById(R.id.tv_car_name);
            carSeats = itemView.findViewById(R.id.tv_car_seats);
            carReleaseYear = itemView.findViewById(R.id.tv_car_release_year);
            carFuelType = itemView.findViewById(R.id.tv_car_fuel_type);

            viewClickListener = clickListener;
            itemView.setOnClickListener(this);

        }

        public void onBind(Cars car) {
            carName.setText(car.getCarName());
            carSeats.setText(car.getCarSeats());
            carReleaseYear.setText(car.getCarReleaseYear());
            carFuelType.setText(car.getCarFuelType());
        }

        @Override
        public void onClick(View v) {
            viewClickListener.OnItemClick(getAdapterPosition());
        }
    }
}
