package com.example.secondtravelapp.UI;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.secondtravelapp.Models.Travel;
import com.example.secondtravelapp.Repository.ITravelRepository;
import com.example.secondtravelapp.Repository.TravelRepository;

import java.util.List;

public class MainTravelsViewModel extends AndroidViewModel {
    ITravelRepository repository;
    //private MutableLiveData<String> mText;
    private MutableLiveData<List<Travel>> allTravelsLiveData = new MutableLiveData<>();
    //public ITravelRepository getRepository(){
    //  return  repository;
    // }


    public MainTravelsViewModel(Application p) throws Exception {
        super(p);
        repository = /*(ITravelRepository)*/ TravelRepository.getInstance(p);
        ITravelRepository.NotifyToTravelListListener notifyToTravelListListener = new ITravelRepository.NotifyToTravelListListener() {
            @Override
            public void onTravelsChanged() {
                List<Travel> travelList = repository.getAllTravels();
                allTravelsLiveData.setValue(travelList);

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

    public void sentSuggestions(String email, Travel travel) {
        repository.sentSuggestion(email, travel);
    }

    public MutableLiveData<List<Travel>> getAllTravels() {

        return allTravelsLiveData;
    }

    /*public MutableLiveData<List<Travel>> getClientTravels(String name){
        return repository.getClientTravels(name);
    }

    public MutableLiveData<List<Travel>> getCompanyTravels(Double distance, UserLocation location)
    {
        return repository.getCompanyTravels(distance, location);
    }
    public MutableLiveData<Boolean> getIsSuccess()
    {
        return repository.getIsSuccess();
    }

    public LiveData<List<Travel>> getAllHistoryTravels() throws Exception {
        return repository.getAllHistoryTravels();
    }*/
}