package com.example.secondtravelapp.Models;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TravelDataSource implements  ITravelDataSource{
    private static final String TAG = "Firebase";
    private MutableLiveData<Boolean> isSuccess= new MutableLiveData<>();
    private List<Travel> allTravelsList;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference travels = firebaseDatabase.getReference("ExistingTravels");

    private static TravelDataSource instance;

    public static TravelDataSource getInstance() {
        if (instance == null)
            instance = new TravelDataSource();
        return instance;
    }


    private TravelDataSource() {
        allTravelsList = new ArrayList<>();
        travels.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                allTravelsList.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren())
                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                            Travel travel = snapshot1.getValue(Travel.class);
                            allTravelsList.add(travel);
                        }
                }
                if (travelsChangedListener != null)
                    travelsChangedListener.onTravelsChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    private TravelsChangedListener travelsChangedListener;
    public void setTravelsChangedListener(TravelsChangedListener l) {
        travelsChangedListener = l;
    }


    @Override
    public void addTravel(Travel p) {
        String id = travels.push().getKey();
        p.setTravelId(id);
        travels.child(id).setValue(p).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                isSuccess.setValue(true);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                isSuccess.setValue(false);
            }
        });
    }


    public  void removeTravel(String id) {
        travels.child(id).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.e(TAG, "Travel Removed");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, "Failure removing Travel");
            }
        });
    }


    @Override
    public void updateTravel(final Travel toUpdate) {
        removeTravel(toUpdate.getTravelId());
        addTravel(toUpdate);
    }

    @Override
    public List<Travel> getAllTravels() {
        return allTravelsList;
    }

    public MutableLiveData<Boolean> getIsSuccess() {
        return isSuccess;
    }
}
