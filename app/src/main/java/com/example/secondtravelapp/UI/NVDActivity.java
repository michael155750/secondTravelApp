package com.example.secondtravelapp.UI;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.example.secondtravelapp.Models.HistoryDataSource;
import com.example.secondtravelapp.Models.Travel;
import com.example.secondtravelapp.Models.UserLocation;
import com.example.secondtravelapp.R;
import com.example.secondtravelapp.UI.CompanyTravels.CompanyTravelsFragment;
import com.example.secondtravelapp.UI.HistoryTravels.HistoryTravelsFragment;
import com.example.secondtravelapp.UI.RegisteredTravels.RegisteredTravelsFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;


import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
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

        viewModel = new ViewModelProvider(this).get(MainTravelsViewModel.class);
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

        /*
        navigationView.setNavigationItemSelectedListener((menuItem) -> {
            switch (menuItem.getItemId()) {
                case R.id.nav_sign_out:
                    Intent intent = new Intent(NVDActivity.this, LoginActivity.class);
                    startActivity(intent);
                    break;
                case R.id.nav_home:
                    Intent intent1 = new Intent(NVDActivity.this, RegisteredTravelsFragment.class);
                    startActivity(intent1);
                    break;
                case R.id.nav_gallery:
                    Intent intent2 = new Intent(NVDActivity.this, CompanyTravelsFragment.class);
                    startActivity(intent2);
                    break;
                case R.id.nav_slideshow:
                    Intent intent3 = new Intent(NVDActivity.this, HistoryTravelsFragment.class);
                    startActivity(intent3);
                    break;
            }
            return true;
        });
*/

        addInitialData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.n_v_d, menu);
        /*MenuItem signOut = menu.findItem(R.id.nav_sign_out);
        signOut.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });*/
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    /*
     * Add some initial data in order to test if the application is working
     * temporary method for debugging
     */
    private void addInitialData() {
        try {

            Travel t1 = new Travel();
            t1.setClientName("Avi Cohen");
            t1.setClientEmail("avi@gmail.com");
            t1.setClientPhone("021234567");
            t1.setPickupAddress(new UserLocation(1, 2));
            t1.setNumOfPassengers(2);
            t1.travelDateTypeSetter(new Date(2020, 2, 2));
            t1.setChildrenTransportation(true);
            t1.setSafeGuarded(true);
            t1.setDestAddress(new UserLocation(3, 2));
            viewModel.addTravel(t1);

            Travel t2 = new Travel();
            t2.setClientName("Hedva Izhaki");
            t2.setClientEmail("hedva@gmail.com");
            t2.setClientPhone("021224597");
            t2.setPickupAddress(new UserLocation(3, 4));
            t2.setNumOfPassengers(2);
            t2.travelDateTypeSetter(new Date(2021, 1, 2));
            t2.setChildrenTransportation(true);
            t2.setSafeGuarded(true);
            t2.setDestAddress(new UserLocation(7, 2));
            viewModel.addTravel(t2);

            Travel t3 = new Travel();
            t3.setClientName("David Levi");
            t3.setClientEmail("david@gmail.com");
            t3.setClientPhone("084224596");
            t3.setPickupAddress(new UserLocation(9, 9));
            t3.setNumOfPassengers(4);
            t3.travelDateTypeSetter(new Date(2020, 9, 2));
            t3.setChildrenTransportation(false);
            t3.setSafeGuarded(true);
            t3.setDestAddress(new UserLocation(1, 8));
            viewModel.addTravel(t3);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_sign_out:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(NVDActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

        }
        return super.onOptionsItemSelected(item);
    }
}