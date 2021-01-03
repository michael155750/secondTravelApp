package com.example.secondtravelapp.UI;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.secondtravelapp.Models.Travel;
import com.example.secondtravelapp.Models.UserLocation;
import com.example.secondtravelapp.Repository.ITravelRepository;
import com.example.secondtravelapp.Repository.TravelRepository;

import java.util.List;

public class MainTravelsViewModel extends ViewModel {
    ITravelRepository repository;
    //private MutableLiveData<String> mText;

    public MainTravelsViewModel() {
    }

    public MainTravelsViewModel(Application p) {
        repository = (ITravelRepository) TravelRepository.getInstance(p);
        //mText = new MutableLiveData<>();
        //mText.setValue("This is gallery fragment");
    }

    // public LiveData<String> getText() {
    //  return mText;
    // }

    void addTravel(Travel travel)
    {
        repository.addTravel(travel);
    }
    void acceptTravel(Travel travel)
    {
        repository.acceptTravel(travel);
    }
    void sentSuggestions(String email, Travel travel){
        repository.sentSuggestion(email, travel);
    }
    public MutableLiveData<List<Travel>> getAllTravels(){
        return repository.getAllTravels();
    }

    public MutableLiveData<List<Travel>> getClientTravels(String name){
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

    public MutableLiveData<List<Travel>> getAllHistoryTravels(){
        return repository.getAllHistoryTravels();
    }
}
