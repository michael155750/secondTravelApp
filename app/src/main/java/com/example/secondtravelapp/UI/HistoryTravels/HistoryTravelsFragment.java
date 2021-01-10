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
import com.example.secondtravelapp.UI.MainTravelsViewModel;

import java.util.List;


public class HistoryTravelsFragment extends Fragment {

    private MainTravelsViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        viewModel =
                new ViewModelProvider(this).get(MainTravelsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_history_travels, container, false);

        LiveData<List<Travel>> historyTravels = null;
        try {
            historyTravels = viewModel.getAllHistoryTravels();
        } catch (Exception e) {
            e.printStackTrace();
        }
        historyTravels.observe(getViewLifecycleOwner(), new Observer<List<Travel>>() {
            @Override
            public void onChanged(List<Travel> travelList) {
                TextView textView = (TextView) root.findViewById(R.id.history_text_view);
                String builder = "";
                for (Travel travel: travelList)
                    builder.concat(travel.getClientName()).concat("\n");
                textView.setText(builder);
            }
        });
        //ListView itemsListView = (ListView)findViewById(R.id.list_view_items);
        //CustomListAdapter adapter = new CustomListAdapter(this, generateItemsList());



        return root;
    }
}