package com.example.secondtravelapp.UI.CompanyTravels;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.secondtravelapp.Models.Travel;
import com.example.secondtravelapp.R;
import com.example.secondtravelapp.UI.MainTravelsViewModel;

import java.util.LinkedList;
import java.util.List;

public class CompanyTravelsFragment extends Fragment {

    private MainTravelsViewModel viewModel;
    List<Travel> travels = new LinkedList<Travel>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        viewModel =
                new ViewModelProvider(this).get(MainTravelsViewModel.class);
        //move to on click for get the details of the company
        /*viewModel.getCompanyTravels(distance, location).observe(this, new Observer<List<Travel>>() {
            @Override
            public void onChanged(List<Travel> temp) {
                travels.clear();
                travels.addAll(temp);
            }
        });*/


        View root = inflater.inflate(R.layout.fragment_company_travels, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        /*viewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
}