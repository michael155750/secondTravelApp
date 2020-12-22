package com.example.secondtravelapp.Repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;


import com.example.secondtravelapp.Models.HistoryDataSource;
import com.example.secondtravelapp.Models.IHistoryDataSource;
import com.example.secondtravelapp.Models.ITravelDataSource;
import com.example.secondtravelapp.Models.Travel;
import com.example.secondtravelapp.Models.TravelDataSource;

import java.util.List;

public class TravelRepository implements ITravelRepository {

    ITravelDataSource  travelDataSource;
    IHistoryDataSource historyDataSource;

    private MutableLiveData<List<Travel>> mutableLiveData = new MutableLiveData<>();

    private static TravelRepository instance;
    public static TravelRepository getInstance(Application application) {
        if (instance == null)
            instance = new TravelRepository(application);
        return instance;
    }

    private TravelRepository(Application application) {
        travelDataSource = TravelDataSource.getInstance();
        historyDataSource = new HistoryDataSource(application.getApplicationContext());

        ITravelDataSource.TravelsChangedListener travelsChangedListener =
                new ITravelDataSource.TravelsChangedListener() {
            @Override
            public void onTravelsChanged() {
                List<Travel> travelList = travelDataSource.getAllTravels();

                mutableLiveData.setValue(travelList);//here we need to change by the project instructions

                //only relevant travels
                historyDataSource.clearTable();
                historyDataSource.addTravel(travelList);

            }
        };

        travelDataSource.setTravelsChangedListener(travelsChangedListener);
    }

    @Override
    public void addTravel(Travel travel) {
        travelDataSource.addTravel(travel);
    }

    @Override
    public void updateTravel(Travel travel) {
        travelDataSource.updateTravel(travel);
    }

   //change to three kinds of data by 3 view models
    @Override
    public MutableLiveData<List<Travel>> getAllTravels() {
        return mutableLiveData;
    }

    @Override
    public MutableLiveData<Boolean> getIsSuccess() {
        return travelDataSource.getIsSuccess();
    }
}
