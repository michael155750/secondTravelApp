package com.example.secondtravelapp.Repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import androidx.lifecycle.MutableLiveData;


import com.example.secondtravelapp.Models.Travel;
import com.example.secondtravelapp.Models.UserLocation;

import java.util.List;

/**
 * @brief interface represents travel repository interface
 * which includes some useful functions in application
 */
public interface ITravelRepository {
    /**
     * @brief adds travel
     * @param travel
     */
    void addTravel(Travel travel);

    void updateTravel(Travel travel);

    /**
     * @brief accept travel company
     * @param email - email of company, the id of the company in our case
     * @param travel - given travel
     */
    void acceptCompany(String email, Travel travel);

    /**
     * @brief accept travel (by client)
     * @param travel - given travel filled with details
     */
    void acceptTravel(Travel travel);

    /**
     * adds company to travel
     * @param email - (id of the company) its email
     * @param travel - given travel.
     */
    void sentSuggestion(String email, Travel travel);

    /**
     *         gets all the travels
     * @return MutableLiveData of list of travels
     */
    List<Travel> getAllTravels();

    /**
     *         gets a travels of certain client
     * @param  clientEmail - the email of the customer
     * @return MutableLiveData of list travels
     */
    /*List<Travel> getClientTravels(String clientEmail);

    *//**
     *         gets all the travel that belong to certain company
     * @param  distance -      the distance between the company location (and the pickup address?)
     * @param  currentAddress  the address of the company
     * @return MutableLiveData of list of travels
     *//*
   List<Travel> getCompanyTravels(Double distance, UserLocation currentAddress);

    *//**
     *         get all travels in the history which stored in the data base
     * @return MutableLiveData of list of travels
     */
   // List<Travel> getAllHistoryTravels() throws Exception;

    /**
     *         ???
     * @return MutableLiveData<Boolean>
     */
    MutableLiveData<Boolean> getIsSuccess();

    interface NotifyToTravelListListener {
        void onTravelsChanged();
    }
    void setNotifyToTravelListListener(ITravelRepository.NotifyToTravelListListener l);
}
