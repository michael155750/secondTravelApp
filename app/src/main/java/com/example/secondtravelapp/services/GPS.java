package com.example.secondtravelapp.services;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.example.secondtravelapp.Models.UserLocation;
import com.google.android.gms.maps.model.LatLng;


import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GPS {


    

    //getLocation From Address

    public static UserLocation getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng LatLan = null;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }

            Address location = address.get(0);
            LatLan = new LatLng(location.getLatitude(), location.getLongitude());

        } catch (IOException ex) {

            ex.printStackTrace();
        }
        UserLocation userLocation = new UserLocation(LatLan.latitude, LatLan.longitude);

        return userLocation;
    }


    public static String getPlace(Context context, UserLocation userLocation) {
        Location location = new Location(LocationManager.GPS_PROVIDER);
        location.setLatitude(userLocation.getLat());
        location.setLongitude(userLocation.getLon());

        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);


            if (addresses.size() > 0) {
                String cityName = addresses.get(0).getAddressLine(0);
                String stateName = addresses.get(0).getAddressLine(1);
                String countryName = addresses.get(0).getAddressLine(2);
                return /*stateName + "\n" + */cityName + "\n"/* + countryName*/;
            }

            return "no place: \n (" + location.getLongitude() + " , " + location.getLatitude() + ")";
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return "IOException ...";
    }


    public final static double AVERAGE_RADIUS_OF_EARTH = 6371;
    public static float calculateDistance(double userLat, double userLng, double venueLat, double venueLng) {

        double latDistance = Math.toRadians(userLat - venueLat);
        double lngDistance = Math.toRadians(userLng - venueLng);

        double a = (Math.sin(latDistance / 2) * Math.sin(latDistance / 2)) +
                (Math.cos(Math.toRadians(userLat))) *
                        (Math.cos(Math.toRadians(venueLat))) *
                        (Math.sin(lngDistance / 2)) *
                        (Math.sin(lngDistance / 2));

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return (float) (Math.round(AVERAGE_RADIUS_OF_EARTH * c));

    }
}