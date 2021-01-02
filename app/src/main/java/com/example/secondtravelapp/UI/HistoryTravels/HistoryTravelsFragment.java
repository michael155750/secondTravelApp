package com.example.secondtravelapp.UI.HistoryTravels;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ListView;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.view.View;

import com.example.secondtravelapp.R;
import com.example.secondtravelapp.UI.MainTravelsViewModel;


public class HistoryTravelsFragment extends Fragment {

    private MainTravelsViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(MainTravelsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_history_travels, container, false);

        //ListView itemsListView = (ListView)findViewById(R.id.list_view_items);
        //CustomListAdapter adapter = new CustomListAdapter(this, generateItemsList());



        return root;
    }
}