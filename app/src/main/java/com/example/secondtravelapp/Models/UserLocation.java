package com.example.secondtravelapp.Models;



import android.location.Location;

public class UserLocation {
    private Double lat;
    private Double lon;

    public UserLocation(UserLocation location) {
        lat = location.getLat();
        lon = location.getLon();
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public UserLocation(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public UserLocation() {
    }

    public static UserLocation convertFromLocation(Location location){
        if (location==null)
            return null;
        return new UserLocation(location.getLatitude(),location.getLongitude());
    }
}


