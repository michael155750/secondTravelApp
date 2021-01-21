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

       /* ArrayList<Travel> mData = new ArrayList<>();
        Travel t1 = new Travel();
        t1.setClientName("Avi Cohen");
        t1.setClientEmail("avi@gmail.com");
        t1.setClientPhone("021234567");

        t1.setPickupAddress(GPS.getLocationFromAddress(this.getContext(),"Stern 33 Jerusalem Israel"));

        t1.setNumOfPassengers(2);
        t1.travelDateTypeSetter(new Date(2020, 2, 2));
        t1.setChildrenTransportation(true);
        t1.setSafeGuarded(true);

        t1.setDestAddress(GPS.getLocationFromAddress(this.getContext(),"Misgav Ladach, Jerusalem, Israel"));
        t1.setStatus(Travel.RequestType.paid);
        mData.add(t1);

        Travel t2 = new Travel();
        t2.setClientName("Hedva Izhaki");
        t2.setClientEmail("hedva@gmail.com");
        t2.setClientPhone("021224597");
        t2.setPickupAddress(GPS.getLocationFromAddress(this.getContext(),"Stern 33 Jerusalem Israel"));
        t2.setNumOfPassengers(2);
        t2.travelDateTypeSetter(new Date(2021, 1, 2));
        t2.setChildrenTransportation(true);
        t2.setSafeGuarded(true);
        t2.setDestAddress(GPS.getLocationFromAddress(this.getContext(),"Misgav Ladach, Jerusalem, Israel"));
        t2.setStatus(Travel.RequestType.close);
        mData.add(t2);

        Travel t3 = new Travel();
        t3.setClientName("David Levi");
        t3.setClientEmail("david@gmail.com");
        t3.setClientPhone("084224596");
        t3.setPickupAddress(GPS.getLocationFromAddress(this.getContext(),"Stern 33 Jerusalem Israel"));
        t3.setNumOfPassengers(4);
        t3.travelDateTypeSetter(new Date(2020, 9, 2));
        t3.setChildrenTransportation(false);
        t3.setSafeGuarded(true);
        t3.setDestAddress(GPS.getLocationFromAddress(this.getContext(),"Misgav Ladach, Jerusalem, Israel"));
        mData.add(t3);

        Travel t4 = new Travel();
        t4.setClientName("Avi Cohen");
        t4.setClientEmail("avi@gmail.com");
        t4.setClientPhone("021234567");

        t4.setPickupAddress(GPS.getLocationFromAddress(this.getContext(),"Stern 33 Jerusalem Israel"));

        t4.setNumOfPassengers(2);
        t4.travelDateTypeSetter(new Date(2020, 2, 2));
        t4.setChildrenTransportation(true);
        t4.setSafeGuarded(true);

        t4.setDestAddress(GPS.getLocationFromAddress(this.getContext(),"Misgav Ladach, Jerusalem, Israel"));
        t4.setStatus(Travel.RequestType.paid);
        mData.add(t4);
        Travel t5 = new Travel();
        t5.setClientName("Avi Cohen");
        t5.setClientEmail("avi@gmail.com");
        t5.setClientPhone("021234567");

        t5.setPickupAddress(GPS.getLocationFromAddress(this.getContext(),"Stern 33 Jerusalem Israel"));

        t5.setNumOfPassengers(2);
        t5.travelDateTypeSetter(new Date(2020, 2, 2));
        t5.setChildrenTransportation(true);
        t5.setSafeGuarded(true);

        t5.setDestAddress(GPS.getLocationFromAddress(this.getContext(),"Misgav Ladach, Jerusalem, Israel"));
        t5.setStatus(Travel.RequestType.paid);
        mData.add(t5);
        Travel t6 = new Travel();
        t6.setClientName("Avi Cohen");
        t6.setClientEmail("avi@gmail.com");
        t6.setClientPhone("021234567");

        t6.setPickupAddress(GPS.getLocationFromAddress(this.getContext(),"Stern 33 Jerusalem Israel"));

        t6.setNumOfPassengers(2);
        t6.travelDateTypeSetter(new Date(2020, 2, 2));
        t6.setChildrenTransportation(true);
        t6.setSafeGuarded(true);

        t6.setDestAddress(GPS.getLocationFromAddress(this.getContext(),"Misgav Ladach, Jerusalem, Israel"));
        t6.setStatus(Travel.RequestType.paid);
        mData.add(t6);
        Travel t7 = new Travel();
        t7.setClientName("Avi Cohen");
        t7.setClientEmail("avi@gmail.com");
        t7.setClientPhone("021234567");

        t7.setPickupAddress(GPS.getLocationFromAddress(this.getContext(),"Stern 33 Jerusalem Israel"));

        t7.setNumOfPassengers(2);
        t7.travelDateTypeSetter(new Date(2020, 2, 2));
        t7.setChildrenTransportation(true);
        t7.setSafeGuarded(true);

        t7.setDestAddress(GPS.getLocationFromAddress(this.getContext(),"Misgav Ladach, Jerusalem, Israel"));
        t7.setStatus(Travel.RequestType.paid);
        mData.add(t7);
        Travel t8 = new Travel();
        t8.setClientName("Avi Cohen");
        t8.setClientEmail("avi@gmail.com");
        t8.setClientPhone("021234567");

        t8.setPickupAddress(GPS.getLocationFromAddress(this.getContext(),"Stern 33 Jerusalem Israel"));

        t8.setNumOfPassengers(2);
        t8.travelDateTypeSetter(new Date(2020, 2, 2));
        t8.setChildrenTransportation(true);
        t8.setSafeGuarded(true);

        t8.setDestAddress(GPS.getLocationFromAddress(this.getContext(),"Misgav Ladach, Jerusalem, Israel"));
        t8.setStatus(Travel.RequestType.paid);
        mData.add(t8);
        Travel t9 = new Travel();
        t9.setClientName("Avi Cohen");
        t9.setClientEmail("avi@gmail.com");
        t9.setClientPhone("021234567");

        t9.setPickupAddress(GPS.getLocationFromAddress(this.getContext(),"Stern 33 Jerusalem Israel"));

        t9.setNumOfPassengers(2);
        t9.travelDateTypeSetter(new Date(2020, 2, 2));
        t9.setChildrenTransportation(true);
        t9.setSafeGuarded(true);

        t9.setDestAddress(GPS.getLocationFromAddress(this.getContext(),"Misgav Ladach, Jerusalem, Israel"));
        t9.setStatus(Travel.RequestType.paid);
        mData.add(t9);

        Travel t11 = new Travel();
        t11.setClientName("Avi Cohen");
        t11.setClientEmail("avi@gmail.com");
        t11.setClientPhone("021234567");

        t11.setPickupAddress(GPS.getLocationFromAddress(this.getContext(),"Stern 33 Jerusalem Israel"));

        t11.setNumOfPassengers(2);
        t11.travelDateTypeSetter(new Date(2020, 2, 2));
        t11.arrivalDateTypeSetter(new Date(2020, 2, 15));
        t11.setChildrenTransportation(true);
        t11.setSafeGuarded(true);

        t11.setDestAddress(GPS.getLocationFromAddress(this.getContext(),"Misgav Ladach, Jerusalem, Israel"));
        t11.setStatus(Travel.RequestType.paid);
        mData.add(t11);*/

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