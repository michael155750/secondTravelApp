package com.example.secondtravelapp.UI.RegisteredTravels;

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

import com.example.secondtravelapp.R;
import com.example.secondtravelapp.UI.MainTravelsViewModel;
/**
 * class registered travels fragment represents

 */
public class RegisteredTravelsFragment extends Fragment {

    private MainTravelsViewModel galleryViewModel;

    /**
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(MainTravelsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_registered_travels, container, false);
        return root;
    }
}