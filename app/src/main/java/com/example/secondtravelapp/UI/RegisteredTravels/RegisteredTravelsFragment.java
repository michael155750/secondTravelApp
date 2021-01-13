package com.example.secondtravelapp.UI.RegisteredTravels;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.secondtravelapp.Models.Travel;
import com.example.secondtravelapp.R;
import com.example.secondtravelapp.UI.MainTravelsViewModel;

import java.util.ArrayList;
import java.util.Date;

/**
 * class registered travels fragment represents

 */
public class RegisteredTravelsFragment extends Fragment {

    private MainTravelsViewModel viewModel;
    public RegisteredCustomListAdapter adapter;
    private ListView listView;


    /**
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel =
                new ViewModelProvider(this).get(MainTravelsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_registered_travels, container, false);
        listView = (ListView) root.findViewById(R.id.listview_registered);


        //
        ArrayList<Travel> mData = new ArrayList<>();
        Travel t1 = new Travel();
        t1.setClientName("Avi Cohen");
        t1.setClientEmail("avi@gmail.com");
        t1.setClientPhone("021234567");
        //t1.setPickupAddress(new UserLocation(1, 2));
        t1.setNumOfPassengers(2);
        t1.travelDateTypeSetter(new Date(2020, 2, 2));
        t1.setChildrenTransportation(true);
        t1.setSafeGuarded(true);
        //t1.setSource("Jerusalem, Hacotel");
        //t1.setDest("Ramat Gan");
        //t1.setDestAddress(new UserLocation(3, 2));
        t1.setStatus(Travel.RequestType.paid);
        mData.add(t1);

        Travel t2 = new Travel();
        t2.setClientName("Hedva Izhaki");
        t2.setClientEmail("hedva@gmail.com");
        t2.setClientPhone("021224597");
        //t2.setPickupAddress(new UserLocation(3, 4));
        t2.setNumOfPassengers(2);
        t2.travelDateTypeSetter(new Date(2021, 1, 2));
        t2.setChildrenTransportation(true);
        t2.setSafeGuarded(true);
        //t2.setSource("Tel Aviv");
        //t2.setDest("Machon Lev");
        //t2.setDestAddress(new UserLocation(7, 2));
        t2.setStatus(Travel.RequestType.close);
        mData.add(t2);

        Travel t3 = new Travel();
        t3.setClientName("David Levi");
        t3.setClientEmail("david@gmail.com");
        t3.setClientPhone("084224596");
        //t3.setPickupAddress(new UserLocation(9, 9));
        t3.setNumOfPassengers(4);
        t3.travelDateTypeSetter(new Date(2020, 9, 2));
        t3.setChildrenTransportation(false);
        t3.setSafeGuarded(true);
        //t3.setSource("Jerusalem, Hacotel");
        //t3.setDest("Machon Lev");
        //t3.setDestAddress(new UserLocation(1, 8));
        mData.add(t3);

        ArrayList<String> mSpinnerData = new ArrayList<>();
        //mSpinnerData.add(0, "Chose company");//Select
        mSpinnerData.add("Dan");
        mSpinnerData.add("Egged");
        mSpinnerData.add("Metropolin");
        mSpinnerData.add("Kavim");

        ArrayList<String> typeSpinnerData = new ArrayList<>();
        //typeSpinnerData.add(0, "Select");//Accepted
        typeSpinnerData.add("Accepted");
        typeSpinnerData.add("Run");
        typeSpinnerData.add("Close");
        typeSpinnerData.add("Paid");


        adapter = new RegisteredCustomListAdapter(mData, mSpinnerData, typeSpinnerData, getActivity());
        listView.setAdapter(adapter);
        return root;
    }
}