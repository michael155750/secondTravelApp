package com.example.secondtravelapp.Repository;

import android.app.Application;
import android.widget.TextView;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.basictemplateapp2.Model.ITravelDataSource;
import com.example.basictemplateapp2.Model.RoomDatabaseHelper;
import com.example.basictemplateapp2.Model.Travel;
import com.example.basictemplateapp2.Model.TravelFirebaseDataSource;
import com.example.secondtravelapp.Models.HistoryDatabaseHelper;
import com.example.secondtravelapp.Models.ITravelDataSource;
import com.example.secondtravelapp.Models.TravelDataSource;

import java.util.List;

public class TravelRepository {

    ITravelDataSource  travelDataSource;
    private MutableLiveData<List<Travel>> mutableLiveData = new MutableLiveData<>();
    private ITravelDataSource parcelDataSource;
    private HistoryDatabaseHelper databaseHelper;
    private static TravelRepository instance;
    public static TravelRepository getInstance(Application application) {
        if (instance == null)
            instance = new TravelRepository(application);
        return instance;
    }

    private TravelRepository(Application application) {
        travelDataSource = TravelDataSource.getInstance();
        databaseHelper = new HistoryDatabaseHelper(application.getApplicationContext());

        ITravelDataSource.TravelsChangedListener travelsChangedListener = new ITravelDataSource.TravelsChangedListener() {
            @Override
            public void onTravelsChanged() {
                List<Travel> travelList = travelDataSource.getAllTravels();
                mutableLiveData.setValue(travelList);
                databaseHelper.clearTable();
                databaseHelper.addTravel(travelList);

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

    @Override
    public MutableLiveData<List<Travel>> getAllTravels() {
        return mutableLiveData;
    }

    @Override
    public MutableLiveData<Boolean> getIsSuccess() {
        return travelDataSource.getIsSuccess();
    }
}
