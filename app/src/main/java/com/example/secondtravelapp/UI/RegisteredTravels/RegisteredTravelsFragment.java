package com.example.secondtravelapp.UI.RegisteredTravels;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;


import com.example.secondtravelapp.Models.Travel;
import com.example.secondtravelapp.Models.UserLocation;
import com.example.secondtravelapp.R;
import com.example.secondtravelapp.Repository.TravelRepository;
import com.example.secondtravelapp.UI.CompanyTravels.CompanyCustomListAdapter;
import com.example.secondtravelapp.UI.NVDActivity;
import com.example.secondtravelapp.services.GPS;
import com.example.secondtravelapp.UI.MainTravelsViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * class registered travels fragment represents
 */

public class RegisteredTravelsFragment extends Fragment {

    private MainTravelsViewModel viewModel;
    public RegisteredCustomListAdapter adapter;
    private ListView listView;
    Context context;
    NVDActivity activity;
    String email;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        activity = ((NVDActivity) getActivity());
        email = activity.getEmail();


        viewModel = ViewModelProviders.of(getActivity()).get(MainTravelsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_registered_travels, container, false);
        listView = (ListView) root.findViewById(R.id.listview_registered);




        ArrayList<Travel> mData = new ArrayList<>();


        //MutableLiveData<List<Travel>> travels = viewModel.getClientTravels(email);
         viewModel.getClientTravels(email).observe(getViewLifecycleOwner(), new Observer<List<Travel>>() {
            @Override
            public void onChanged(List<Travel> travelList) {
                mData.addAll(travelList);
            }
        });



        ArrayList<String> mSpinnerData = new ArrayList<>();



        ArrayList<String> typeSpinnerData = new ArrayList<>();
        typeSpinnerData.add(0, "Select");//Accepted
        typeSpinnerData.add("Accepted");
        typeSpinnerData.add("Run");
        typeSpinnerData.add("Close");


        adapter = new RegisteredCustomListAdapter(mData, mSpinnerData, typeSpinnerData, this.getContext());

        adapter.setCompanyListener(new RegisteredCustomListAdapter.CompanyListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onButtonClicked(int position, String email, View view) {
                if(!email.equals("Choose company") && view != null) {
                    mData.get(position).changeCompanyValue(email);
                    viewModel.updateTravel(mData.get(position));
                }
            }
        });

        adapter.setTypeListener(new RegisteredCustomListAdapter.TypeListener() {
            @Override
            public void onButtonClicked(int position, int spinnerPosition, View view) {

                if (view != null) {
                    switch (spinnerPosition) {
                        case 1:
                            mData.get(position).setStatus(Travel.RequestType.accepted);
                            viewModel.updateTravel(mData.get(position));
                            break;
                        case 2:
                            mData.get(position).setStatus(Travel.RequestType.run);
                            viewModel.updateTravel(mData.get(position));
                            break;
                        case 3:
                            mData.get(position).setStatus(Travel.RequestType.close);
                            viewModel.updateTravel(mData.get(position));
                            break;
                    }

                    //mData.get(position).setStatus(st(spinnerPosition));
                }

            }
        });


        listView.setAdapter(adapter);
        return root;
    }
}