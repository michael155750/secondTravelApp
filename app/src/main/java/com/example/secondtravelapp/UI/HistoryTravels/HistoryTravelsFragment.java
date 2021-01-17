package com.example.secondtravelapp.UI.HistoryTravels;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.secondtravelapp.Models.Travel;
import com.example.secondtravelapp.R;
import com.example.secondtravelapp.UI.CompanyTravels.CompanyCustomListAdapter;
import com.example.secondtravelapp.UI.MainTravelsViewModel;
import com.example.secondtravelapp.services.GPS;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class HistoryTravelsFragment extends Fragment {

    private MainTravelsViewModel viewModel;

    ListView listView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        viewModel =
                new ViewModelProvider(this).get(MainTravelsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_history_travels, container, false);

        listView = root.findViewById(R.id.list_view_items);
       /* LiveData<List<Travel>> historyTravels = null;
        try {
         //   historyTravels = viewModel.getAllHistoryTravels();
        } catch (Exception e) {
            e.printStackTrace();
        }
        historyTravels.observe(getViewLifecycleOwner(), new Observer<List<Travel>>() {
            @Override
            public void onChanged(List<Travel> travelList) {
               /* TextView textView = (TextView) root.findViewById(R.id.histo);
                String builder = "";
                for (Travel travel: travelList)
                    builder.concat(travel.getClientName()).concat("\n");
                textView.setText(builder);


            }
        });*/
        //ListView itemsListView = (ListView)findViewById(R.id.list_view_items);
        //CustomListAdapter adapter = new CustomListAdapter(this, generateItemsList());

        /*
        ArrayList<Travel> mData = new ArrayList<>();
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
        t11.setChildrenTransportation(true);
        t11.setSafeGuarded(true);

        t11.setDestAddress(GPS.getLocationFromAddress(this.getContext(),"Misgav Ladach, Jerusalem, Israel"));
        t11.setStatus(Travel.RequestType.paid);
        mData.add(t11);

        HistoryCustomListAdapter adapter = new HistoryCustomListAdapter(this.getContext(), mData);
        listView.setAdapter(adapter);
*/

        return root;
    }
}