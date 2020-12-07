package com.example.secondtravelapp.UI.RegisteredTravels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegisteredTravelsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RegisteredTravelsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}