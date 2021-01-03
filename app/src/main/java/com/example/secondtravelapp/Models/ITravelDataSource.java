package com.example.secondtravelapp.Models;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

public interface ITravelDataSource {
    void addTravel(Travel travel);
    void updateTravel(Travel travel);
    List<Travel> getAllTravels();
    MutableLiveData<Boolean> getIsSuccess();
    interface TravelsChangedListener {
        void onTravelsChanged();
    }
    void setTravelsChangedListener(TravelsChangedListener l);
}