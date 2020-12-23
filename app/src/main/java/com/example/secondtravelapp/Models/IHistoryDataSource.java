package com.example.secondtravelapp.Models;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface IHistoryDataSource {
    public LiveData<List<Travel>> getTravels();
    public LiveData<Travel> getTravel(String id);
    public void addTravel(Travel p);
    public void addTravel(List<Travel> travelList);
    public void editTravel(Travel p);
    public void deleteTravel(Travel p);
    public void clearTable();
}
