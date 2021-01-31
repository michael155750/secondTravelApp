package com.example.secondtravelapp.Models;

import android.content.Context;
import androidx.lifecycle.LiveData;
import java.util.List;

public class HistoryDataSource implements IHistoryDataSource {
    private TravelDao travelDao;

    public HistoryDataSource(Context context) throws Exception{
        RoomDataSource database= RoomDataSource.getInstance(context);
        travelDao =database.getTravelDao();
        travelDao.clear();
    }

    public List<Travel> getTravels() throws Exception{
        return travelDao.getAll();

    }

    public Travel getTravel(String id) throws Exception{
        return travelDao.get(id);
    }

    public void addTravel(Travel p) throws Exception{
        travelDao.insert(p);
    }

    public void addTravel(List<Travel> travelList) throws Exception{
        travelDao.insert(travelList);
    }

    public void editTravel(Travel p) throws Exception{
        travelDao.update(p);
    }

    public void deleteTravel(Travel p) throws Exception{
        travelDao.delete(p);
    }

    public void clearTable() throws Exception{travelDao.clear();}
}
