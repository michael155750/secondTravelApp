package com.example.secondtravelapp.UI.HistoryTravels;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.secondtravelapp.Models.Travel;
import com.example.secondtravelapp.R;
import com.example.secondtravelapp.UI.CompanyTravels.CompanyCustomListAdapter;
import com.example.secondtravelapp.UI.MainTravelsViewModel;
import com.example.secondtravelapp.UI.NVDActivity;
import com.example.secondtravelapp.UI.RegisteredTravels.RegisteredCustomListAdapter;
import com.example.secondtravelapp.services.GPS;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class HistoryTravelsFragment extends Fragment {

    Context context;
    //boolean accessPermission;
    //ArrayList<String> administrators;
    private MainTravelsViewModel viewModel;
    //NVDActivity activity;
    //String email;
    ArrayList<Travel> mData;

    ListView listView;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {





        viewModel = ViewModelProviders.of(getActivity()).get(MainTravelsViewModel.class);
        View root;
        // if (accessPermission) {

        root = inflater.inflate(R.layout.fragment_history_travels, container, false);

        listView = root.findViewById(R.id.list_view_items);

        mData = new ArrayList<>();

        try {
            viewModel.getAllHistoryTravels().observe(getViewLifecycleOwner(), new Observer<List<Travel>>() {
                //viewModel.getAllTravels().observe(getViewLifecycleOwner(), new Observer<List<Travel>>() {
                @Override
                public void onChanged(List<Travel> travelList) {
                    mData.addAll(travelList);
                    //TextView textView = (TextView) root.findViewById(R.id.histo);
                    //String builder = "";
                    //for (Travel travel: travelList)
                    //   builder.concat(travel.getClientName()).concat("\n");
                    //textView.setText(builder);


                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }




        HistoryCustomListAdapter adapter = new HistoryCustomListAdapter(this.getContext(), mData);

        adapter.setListener(new HistoryCustomListAdapter.HistoryTravelListener() {
            @Override
            public void onButtonClicked(int position, View view) {
                if (view.getId() == R.id.change_status_button) {
                    mData.get(position).setStatus(Travel.RequestType.paid);
                    viewModel.updateTravel(mData.get(position));
                    Toast.makeText(getContext(), "Status changed to paid", Toast.LENGTH_LONG).show();


                }

                if (view.getId() == R.id.email_button) {
                    // String company = Travel.CompanyConverter.asString(mData.get(position).getCompany());
                    String companyEmail = "";
                    for (String email : mData.get(position).getCompany().keySet()) {
                        if (mData.get(position).getCompany().get(email))
                            companyEmail = email;
                    }


                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.setData(Uri.parse("mailto:"));
                    emailIntent.setType("text/plain");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, companyEmail);

                    try {
                        startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                        //finish();

                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(getContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        listView.setAdapter(adapter);

        return root;
    }
}