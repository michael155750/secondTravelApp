package com.example.secondtravelapp.UI.CompanyTravels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CompanyTravelViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CompanyTravelViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}