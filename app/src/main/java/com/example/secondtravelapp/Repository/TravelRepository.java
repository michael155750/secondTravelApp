package com.example.secondtravelapp.Repository;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.secondtravelapp.Models.HistoryDataSource;
import com.example.secondtravelapp.Models.IHistoryDataSource;
import com.example.secondtravelapp.Models.ITravelDataSource;
import com.example.secondtravelapp.Models.Travel;
import com.example.secondtravelapp.Models.TravelDataSource;
import com.example.secondtravelapp.Models.UserLocation;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.sqrt;

public class TravelRepository implements ITravelRepository {

    ITravelDataSource travelDataSource;
    IHistoryDataSource historyDataSource;
    private ITravelRepository.NotifyToTravelListListener notifyToTravelListListenerRepository;

    private MutableLiveData<List<Travel>> mutableLiveData = new MutableLiveData<>();
    List<Travel> travelList;
    private static TravelRepository instance;

    public static TravelRepository getInstance(Application application) throws Exception {
        if (instance == null)
            instance = new TravelRepository(application);
        return instance;
    }

    private TravelRepository(Application application) throws Exception {
        travelDataSource = TravelDataSource.getInstance();
        historyDataSource = new HistoryDataSource(application.getApplicationContext());


        ITravelDataSource.TravelsChangedListener travelsChangedListener =
                new ITravelDataSource.TravelsChangedListener() {
                    @Override
                    public void onTravelsChanged() throws Exception {
                        travelList = travelDataSource.getAllTravels();
                        // travelList.clear();
                        //travelList.addAll(travelDataSource.getAllTravels());
                        //mutableLiveData.setValue(travelList);

                        //remove all non relevant travels from travelList
                        List<Travel> historyTravelList = new LinkedList<Travel>();
                        for (Travel travel : travelList) {
                            historyTravelList.add(travel);
                        }

                        for (Iterator<Travel> iterator = historyTravelList.iterator(); iterator.hasNext(); ) {
                            Travel travel = iterator.next();
                            if (travel.getStatus() != Travel.RequestType.close &&
                                    travel.getStatus() != Travel.RequestType.paid) {
                                iterator.remove();
                            }
                        }
                        historyDataSource.clearTable();
                        historyDataSource.addTravel(historyTravelList);

                        if (notifyToTravelListListenerRepository != null)
                            notifyToTravelListListenerRepository.onTravelsChanged();
                    }
                };

        travelDataSource.setTravelsChangedListener(travelsChangedListener);
    }

    @Override
    public void updateTravel(Travel travel) {
        travelDataSource.updateTravel(travel);
    }

    @Override
    public void addTravel(Travel travel) {
        travelDataSource.addTravel(travel);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void acceptCompany(String email, Travel travel) {
        travel.changeCompanyValue(email);
        travelDataSource.updateTravel(travel);
    }


    @Override
    public void acceptTravel(Travel travel) {
        travel.setStatus(Travel.RequestType.accepted);
        travelDataSource.updateTravel(travel);
    }

    @Override
    public void sentSuggestion(String email, Travel travel) {
        travel.setOneCompany(email);
        travelDataSource.updateTravel(travel);
    }

    //change to three kinds of data by 3 view models
    @Override
    public List<Travel> getAllTravels() {
        return travelList;
    }

   /* @Override
    public List<Travel> getClientTravels(String clientEmail) {
        List<Travel> clientTravelList = new LinkedList<Travel>();
        for (Travel travel : travelList) {
            if (travel.getClientEmail() == clientEmail &&
                    travel.getStatus() == Travel.RequestType.sent) {
                clientTravelList.add(travel);
            }
        }
        return clientTravelList;

    }*/

//    @Override
//    public List<Travel> getCompanyTravels(Double distance, UserLocation currentAddress) {
//        List<Travel> companyTravelList = new LinkedList<Travel>();
//        Double companyDistance = 0d;
//        Double distanceX = 0d;
//        Double distanceY = 0d;
//        for (Travel travel : travelList) {
//            distanceX = (travel.getPickupAddress().getLon() - currentAddress.getLon());
//            distanceY = (travel.getPickupAddress().getLat() - currentAddress.getLat());
//            companyDistance = sqrt(distanceX * distanceX + distanceY * distanceY);
//            if (companyDistance <= distance && travel.getStatus() == Travel.RequestType.sent) {
//
//                companyTravelList.add(travel);
//            }
//
//        }
//        return companyTravelList;
//    }

    @Override
    public LiveData<List<Travel>> getAllHistoryTravels() throws Exception {
        //working with casting

        return historyDataSource.getTravels();
    }


    @Override
    public MutableLiveData<Boolean> getIsSuccess() {
        return travelDataSource.getIsSuccess();
    }


    public void setNotifyToTravelListListener(ITravelRepository.NotifyToTravelListListener l) {
        notifyToTravelListListenerRepository = l;
    }
}