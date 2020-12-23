package com.example.secondtravelapp.Repository;

import androidx.lifecycle.MutableLiveData;

import androidx.lifecycle.MutableLiveData;


import com.example.secondtravelapp.Models.Travel;
import com.example.secondtravelapp.Models.UserLocation;

import java.util.List;

public interface ITravelRepository {
    void addTravel(Travel travel);
    void updateTravel(Travel travel);
    void acceptCompany(Travel travel);
    void acceptTravel(Travel travel);
    void sentSuggestion(Travel travel);
    MutableLiveData<List<Travel>> getAllTravels();
    MutableLiveData<List<Travel>> getClientTravels(String clientEmail);
    MutableLiveData<List<Travel>> getCompanyTravels(Double distance, UserLocation currentAddress);
    MutableLiveData<List<Travel>> getAllHistoryTravels();
    MutableLiveData<Boolean> getIsSuccess();
}
