package com.example.rac.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
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
        private final ImageView carImage;
        private final TextView carName, carSeats, carReleaseYear, carFuelType, carPrice, carAvailability;
        private final RecyclerViewClickListener viewClickListener;

        public CarsViewHolder(@NonNull View itemView, RecyclerViewClickListener clickListener) {
            super(itemView);

            carImage = itemView.findViewById(R.id.iv_car_image);
            carName = itemView.findViewById(R.id.tv_car_name);
            carSeats = itemView.findViewById(R.id.tv_car_seats);
            carReleaseYear = itemView.findViewById(R.id.tv_car_release_year);
            carFuelType = itemView.findViewById(R.id.tv_car_fuel_type);
            carPrice = itemView.findViewById(R.id.tv_car_price);
            carAvailability = itemView.findViewById(R.id.tv_car_unavailable);

            viewClickListener = clickListener;
            itemView.setOnClickListener(this);

        }

        public void onBind(Cars car) {
            //Picasso.get().load(car.getCarImage()).centerCrop().into(carImage);
            carImage.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), car.getCarImage()));
            carName.setText(car.getCarName());
            carSeats.setText(car.getCarSeats());
            carReleaseYear.setText(car.getCarReleaseYear());
            carFuelType.setText(car.getCarFuelType());

            if (!car.isAvailable()) {
                carName.setTextColor(itemView.getResources().getColor(R.color.notAvailable, itemView.getContext().getTheme()));
                carSeats.setTextColor(itemView.getResources().getColor(R.color.notAvailable, itemView.getContext().getTheme()));
                carReleaseYear.setTextColor(itemView.getResources().getColor(R.color.notAvailable, itemView.getContext().getTheme()));
                carFuelType.setTextColor(itemView.getResources().getColor(R.color.notAvailable, itemView.getContext().getTheme()));
                carPrice.setTextColor(itemView.getResources().getColor(R.color.notAvailable, itemView.getContext().getTheme()));
                carAvailability.setVisibility(View.VISIBLE);
            }

            if (car.isSelected()) {
                itemView.setBackground(ContextCompat.getDrawable(itemView.getContext(), R.drawable.car_selected));
            } else {
                itemView.setBackground(ContextCompat.getDrawable(itemView.getContext(), R.drawable.car_list));
            }
        }

        @Override
        public void onClick(View v) {
            viewClickListener.OnItemClick(getAdapterPosition());
        }
    }
}
