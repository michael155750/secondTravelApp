package com.example.secondtravelapp.Models;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface IHistoryDataSource {
    public List<Travel> getTravels() throws Exception;
    public Travel getTravel(String id) throws Exception;
    public void addTravel(Travel p) throws Exception;
    public void addTravel(List<Travel> travelList) throws Exception;
    public void editTravel(Travel p) throws Exception;
    public void deleteTravel(Travel p) throws Exception;
    public void clearTable() throws Exception;
}
