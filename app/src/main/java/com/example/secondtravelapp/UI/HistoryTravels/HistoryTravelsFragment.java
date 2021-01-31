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


       /* administrators.add("asaf0522238528@gmail.com");
        administrators.add("michael155750@gmail.com");
        administrators.add("ezradashtt@gmail.com");

        */
        //activity = ((NVDActivity) getActivity());
        //email = activity.getEmail();
        /*
        for (String adminEmail : administrators) {
            if (email == adminEmail) {
                accessPermission = true;
            }
        }

         */

        /*HashMap<String, String> companiesEmail = new HashMap<String, String>() {
            {
                put("cs@dan.co.il", "Dan");
                put("ins@Egged.co.il", "Egged");
                put("tviot@metropoline.com", "Metropolin");
                put("info@Kavim.com", "Kavim");
            }

        };

         */


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



       /* ArrayList<Travel> mData = new ArrayList<>();
        Travel t1 = new Travel();
        t1.setClientName("Avi Cohen");
        t1.setClientEmail("avi@gmail.com");
        t1.setClientPhone("021234567");

        t1.setOneCompanyTrue("Metropolin@gmail.com");
        t1.setOneCompany("Dan@gmail.com");
        t1.setOneCompany("Egged@gmail.com");
        t1.setOneCompany("Kavim@gmail.com");
        t1.setPickupAddress(GPS.getLocationFromAddress(this.getContext(), "Stern 33 Jerusalem Israel"));

        t1.setNumOfPassengers(2);
        t1.travelDateTypeSetter(new Date(2020, 2, 2));
        t1.setChildrenTransportation(true);
        t1.setSafeGuarded(true);

        t1.setDestAddress(GPS.getLocationFromAddress(this.getContext(), "Misgav Ladach, Jerusalem, Israel"));
        t1.setStatus(Travel.RequestType.paid);
        mData.add(t1);

        Travel t2 = new Travel();
        t2.setClientName("Hedva Izhaki");
        t2.setClientEmail("hedva@gmail.com");
        t2.setClientPhone("021224597");
        t2.setOneCompanyTrue("Metropolin@gmail.com");
        t2.setOneCompany("Dan@gmail.com");
        t2.setOneCompany("Egged@gmail.com");
        t2.setOneCompany("Kavim@gmail.com");
        t2.setNumOfPassengers(2);
        t2.travelDateTypeSetter(new Date(2021, 1, 2));
        t2.setChildrenTransportation(true);
        t2.setSafeGuarded(true);
        t2.setPickupAddress(GPS.getLocationFromAddress(this.getContext(), "Stern 33 Jerusalem Israel"));
        t2.setStatus(Travel.RequestType.close);
        t2.setDestAddress(GPS.getLocationFromAddress(this.getContext(), "Misgav Ladach, Jerusalem, Israel"));
        mData.add(t2);

        Travel t3 = new Travel();
        t3.setClientName("David Levi");
        t3.setClientEmail("david@gmail.com");
        t3.setClientPhone("084224596");

        t3.setOneCompanyTrue("Metropolin@gmail.com");
        t3.setOneCompany("Dan@gmail.com");
        t3.setOneCompany("Egged@gmail.com");
        t3.setOneCompany("Kavim@gmail.com");
        t3.setPickupAddress(GPS.getLocationFromAddress(this.getContext(), "Stern 33 Jerusalem Israel"));
        t3.setNumOfPassengers(4);
        t3.travelDateTypeSetter(new Date(2020, 9, 2));
        t3.setChildrenTransportation(false);
        t3.setSafeGuarded(true);
        t3.setDestAddress(GPS.getLocationFromAddress(this.getContext(), "Misgav Ladach, Jerusalem, Israel"));

        mData.add(t3);
*/
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