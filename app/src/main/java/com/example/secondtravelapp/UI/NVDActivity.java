package com.example.secondtravelapp.UI;

import android.os.Bundle;

import android.view.View;
import android.view.Menu;

import com.example.secondtravelapp.Models.Travel;
import com.example.secondtravelapp.Models.UserLocation;
import com.example.secondtravelapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Date;

public class NVDActivity extends AppCompatActivity {

    private MainTravelsViewModel viewModel;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        viewModel = new  ViewModelProvider(this).get(MainTravelsViewModel.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nvd);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        addInitialData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.n_v_d, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    /**
     * Add some initial data in order to test if the application is working
     * temporary method for debugging
     */
    private void addInitialData(){
       /* try {
            /*viewModel.addTravel(new Travel("Avi Cohen","021234567","avi@gmail.com"
                    ,new UserLocation(1,2), 2,new Date(2020,2,2),
                    true,true, new UserLocation(3,2)));
            viewModel.addTravel(new Travel("David Levi","031234467","david@gmail.com"
                    ,new UserLocation(3,4), 4,new Date(2020,1,2),
                    true,false, new UserLocation(3,8)));
            viewModel.addTravel(new Travel("Hedva Izhaki","021237569","hedva@gmail.com"
                    ,new UserLocation(5,6), 3,new Date(2020,3,1),
                    false,true, new UserLocation(10,20)));
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }
}