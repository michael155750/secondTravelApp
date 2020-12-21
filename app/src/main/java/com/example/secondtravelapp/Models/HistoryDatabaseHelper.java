package com.example.secondtravelapp.Models;

import android.content.Context;
import androidx.lifecycle.LiveData;
import java.util.List;

public class HistoryDatabaseHelper {
    private TravelDao travelDao;

    public HistoryDatabaseHelper(Context context){
        HistoryDataSource database= HistoryDataSource.getInstance(context);
        travelDao =database.getTravelDao();
        travelDao.clear();
    }

    public LiveData<List<Travel>> getTravels(){
        return travelDao.getAll();
    }

    public LiveData<Travel> getTravel(String id){
        return travelDao.get(id);
    }

    public void addTravel(Travel p) {
        travelDao.insert(p);
    }

    public void addTravel(List<Travel> travelList) {
        travelDao.insert(travelList);
    }

    public void editTravel(Travel p) {
        travelDao.update(p);
    }

    public void deleteTravel(Travel p){
        travelDao.delete(p);
    }

    public void clearTable(){travelDao.clear();}
}
