package com.example.rac.viewModels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.rac.models.TravelPlan;
import com.example.rac.models.Users;
import com.example.rac.repository.UsersRepository;

import java.util.List;

public class UsersViewModel extends ViewModel {
    private final UsersRepository repository;

    public UsersViewModel(Context context) {
        repository = UsersRepository.getInstance(context);
    }

    public void insertUser(Users users) {
        repository.insertUser(users);
    }

    public LiveData<String> loginUser(Users users) {
        return repository.loginUser(users);
    }

    public LiveData<String> getUserName(String email) {
        return repository.getUserName(email);
    }

    public void saveTravelPlan(TravelPlan travelPlan) {
        repository.saveTravelPlan(travelPlan);
    }

    public LiveData<List<TravelPlan>> getTravelPlans(String email) {
        return repository.getTravelPlans(email);
    }
}
