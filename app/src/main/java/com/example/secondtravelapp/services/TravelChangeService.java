package com.example.secondtravelapp.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleService;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.secondtravelapp.Models.ITravelDataSource;
import com.example.secondtravelapp.Models.TravelDataSource;

public class TravelChangeService extends LifecycleService {
    //Integer sum = 0;
    boolean isThreadOn = false;
    //public final String TAG = "myService";
    ITravelDataSource travelDataSource;



    @Override
    public void onCreate() {
       super.onCreate();
      /*  Toast.makeText(this,"onCreate", Toast.LENGTH_LONG).show();
        Log.d(TAG," onCreate");*/
        try {
            travelDataSource = TravelDataSource.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        if (!isThreadOn) {

            LiveData<Boolean> isSuccess = travelDataSource.getIsSuccess();
            isSuccess.observe((LifecycleOwner) this, new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean element) {


                     if (element){
                         Intent intent1 = new Intent("CustomIntent");

                         intent1.setAction("com.javacodegeeks.android.A_CUSTOM_INTENT");
                         sendBroadcast(intent1);
                     }
                }
            });

           /* isThreadOn = true;
            ActionThread actionThread = new ActionThread();
            actionThread.start();*/
        }
       // Toast.makeText(this,"onStartCommand.+ startId:="+startId+ " sum is: " + sum, Toast.LENGTH_LONG).show();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
       // if(!isThreadOn)
         //   Toast.makeText(this,"onDestroy. sum is:" + sum, Toast.LENGTH_LONG).show();
        //sum=0;
        //Log.d(TAG," onDestroy");
    }

    @Override
    public IBinder onBind(Intent arg0) {
        super.onBind(arg0);
        return null;
    }

  /*  public class ActionThread extends Thread {

        public void run() {


            sum = 0;
            for(Integer idx = 0; idx< 10099; idx ++)
            {
                sum++;
            }
            isThreadOn = false;
        }
    }*/
}
