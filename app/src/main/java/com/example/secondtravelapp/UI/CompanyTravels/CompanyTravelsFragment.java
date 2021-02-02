package com.example.secondtravelapp.UI.CompanyTravels;


import android.annotation.SuppressLint;
import android.app.Activity;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;


import com.example.secondtravelapp.UI.NVDActivity;
import com.google.android.gms.maps.model.LatLng;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.secondtravelapp.Models.Travel;
import com.example.secondtravelapp.Models.UserLocation;
import com.example.secondtravelapp.R;
import com.example.secondtravelapp.UI.MainTravelsViewModel;
import com.example.secondtravelapp.UI.RegisteredTravels.RegisteredCustomListAdapter;
import com.example.secondtravelapp.services.GPS;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static androidx.core.content.ContextCompat.checkSelfPermission;

public class CompanyTravelsFragment extends Fragment {

    private MainTravelsViewModel viewModel;
    ArrayList<Travel> travels = new ArrayList<Travel>();
    private ListView listView;
    UserLocation companyLocation;
    final double distFromCompany = 10;
    String companyEmail;
    LocationManager locationManager;
    LocationListener locationListener;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(MainTravelsViewModel.class);
        companyEmail= ((NVDActivity) getActivity()).getEmail();
        //Find the location of the company
        //if GPS is not working have a default address
        companyLocation = new UserLocation(GPS.getLocationFromAddress(this.getContext(),"אברהם שטרן 33 ירושלים"));
        locationManager = (LocationManager) this.getContext().getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                companyLocation.setLat(location.getLatitude());
                companyLocation.setLon(location.getLongitude());
            }
            public void onStatusChanged(String provider, int status, Bundle extras) {   }

            public void onProviderEnabled(String provider) { }

            public void onProviderDisabled(String provider) { }
        };

        //     Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(this.getContext(),Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 5);

        } else {
            // Android version is lesser than 6.0 or the permission is already granted.

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }

        Thread timer = new Thread() {
            public void run(){
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        timer.start();

        //move to on click for get the details of the company
        viewModel.getCompanyTravels(distFromCompany, companyLocation).observe(getViewLifecycleOwner(), new Observer<List<Travel>>() {
            @Override
            public void onChanged(List<Travel> temp) {

                travels.addAll(temp);
            }
        });


        View root = inflater.inflate(R.layout.fragment_company_travels, container, false);

        listView = (ListView) root.findViewById(R.id.list_view_company);
        /*viewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/



        CompanyCustomListAdapter adapter = new CompanyCustomListAdapter(this.getContext(), travels);

        adapter.setListener(new CompanyCustomListAdapter.CompanyTravelListener() {
            @Override
            public void onButtonClicked(int position, View view) {
                if (view.getId() == R.id.company_call_client) {
                    String phone = travels.get(position).getClientPhone();

                    if (phone.isEmpty()) {
                        Toast.makeText(getContext(), "no phone number exist", Toast.LENGTH_LONG).show();
                    } else {


                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:" + phone));
                        startActivity(callIntent);
                    }

                }
                if (view.getId() == R.id.accept){
                    CheckBox accept = root.findViewById(R.id.accept);


                    if(accept.isChecked()){
                        travels.get(position).setOneCompany(companyEmail);
                        viewModel.updateTravel(travels.get(position));
                    }


                }
            }
        });



        listView.setAdapter(adapter);
        return root;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 5) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

            }
        }

    }
}