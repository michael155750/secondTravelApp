package com.example.secondtravelapp.UI.HistoryTravels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HistoryTravelsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HistoryTravelsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}