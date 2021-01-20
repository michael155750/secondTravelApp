package com.example.secondtravelapp.UI;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.secondtravelapp.Models.Travel;
import com.example.secondtravelapp.Models.UserLocation;
import com.example.secondtravelapp.Repository.ITravelRepository;
import com.example.secondtravelapp.Repository.TravelRepository;
import com.example.secondtravelapp.services.GPS;

import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.sqrt;

public class MainTravelsViewModel extends AndroidViewModel {
    ITravelRepository repository;
    //private MutableLiveData<String> mText;
    private MutableLiveData<List<Travel>> allTravelsLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Travel>> companyTravelsLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Travel>> clientTravelsLiveData = new MutableLiveData<>();
    private List<Travel> allTravelList;
    //public ITravelRepository getRepository(){
    //  return  repository;
    // }


    public MainTravelsViewModel(Application p) throws Exception {
        super(p);
        repository = /*(ITravelRepository)*/ TravelRepository.getInstance(p);
        ITravelRepository.NotifyToTravelListListener notifyToTravelListListener = new ITravelRepository.NotifyToTravelListListener() {
            @Override
            public void onTravelsChanged() {
                allTravelList = repository.getAllTravels();
                allTravelsLiveData.setValue(allTravelList);



            }
        };
        repository.setNotifyToTravelListListener(notifyToTravelListListener);

    }

    // public LiveData<String> getText() {
    //  return mText;
    // }

    public void addTravel(Travel travel) {
        repository.addTravel(travel);
    }

    public void updateTravel(Travel travel){
        repository.updateTravel(travel);
    }

    public void acceptTravel(Travel travel) {
        repository.acceptTravel(travel);
    }

    public void acceptCompany(String email,Travel travel) {
        repository.acceptCompany(email,travel);
    }

    public void sentSuggestions(String email, Travel travel) {
        repository.sentSuggestion(email, travel);
    }

    public MutableLiveData<List<Travel>> getAllTravels() {

        return allTravelsLiveData;
    }

    public MutableLiveData<List<Travel>> getClientTravels(String clientEmail){
        List<Travel> clientTravelList = new LinkedList<Travel>();
        for (Travel travel : allTravelList) {

            if (travel.getClientEmail().equals(clientEmail) &&
                    travel.getStatus() == Travel.RequestType.sent) {
                clientTravelList.add(travel);
            }
        }

        clientTravelsLiveData.setValue(clientTravelList);
        return clientTravelsLiveData;
    }

    public MutableLiveData<List<Travel>> getCompanyTravels(Double distance, UserLocation companyLocation)
    {
        List<Travel> companyTravelList = new LinkedList<Travel>();

        for (Travel travel : allTravelList) {

            if (GPS.calculateDistance(travel.getPickupAddress().getLat(), travel.getPickupAddress().getLon(),
                                                            companyLocation.getLat(), companyLocation.getLon()) <= distance &&
                    travel.getStatus() == Travel.RequestType.sent) {

                companyTravelList.add(travel);
            }

        }
        companyTravelsLiveData.setValue(companyTravelList);
        return companyTravelsLiveData;

    }

    public MutableLiveData<Boolean> getIsSuccess()
    {
        return repository.getIsSuccess();
    }

    public LiveData<List<Travel>> getAllHistoryTravels() throws Exception {
        return repository.getAllHistoryTravels();
    }
}