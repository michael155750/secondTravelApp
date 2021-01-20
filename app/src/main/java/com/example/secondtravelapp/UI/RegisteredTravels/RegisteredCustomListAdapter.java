package com.example.secondtravelapp.UI.RegisteredTravels;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;

import com.example.secondtravelapp.Models.Travel;
import com.example.secondtravelapp.Models.TravelDataSource;
import com.example.secondtravelapp.R;
import com.example.secondtravelapp.UI.CompanyTravels.CompanyCustomListAdapter;
import com.example.secondtravelapp.UI.MainTravelsViewModel;
import com.example.secondtravelapp.services.GPS;


public class RegisteredCustomListAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<String> companySpinnerList;
    private ArrayList<String> statusSpinner;
    private ArrayList<Travel> travels;
    private RegisteredTravelListener listener;
    static boolean lockFirstTimeCompany = false;
    static boolean lockFirstTimeStatus = false;
    private String companyEmail = null;
   

    public RegisteredCustomListAdapter(/*Context context, ArrayList<Travel> travels,
                                       ArrayList<String> spinnerItems, ArrayList<String> type*/
            ArrayList<Travel> travels, ArrayList<String> spinnerItems,
            ArrayList<String> type,
            Context context) {
        this.context = context;
        this.travels = travels;
        this.companySpinnerList = new ArrayList<String>();
        this.statusSpinner = type;
    }

    public interface RegisteredTravelListener {
        void onButtonClicked(int position, int spinnerPosition, View view);
    }

    public void setListener(RegisteredCustomListAdapter.RegisteredTravelListener listener){
        this.listener=listener;
    }

    @Override
    public int getCount() {
        return travels.size(); //returns total item in the list
    }

    @Override
    public Object getItem(int position) {
        return travels.get(position); //returns the item at the specified position
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /*RegisteredCustomListAdapter.ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.history_adapter_row, parent, false);
            //viewHolder = new RegisteredCustomListAdapter.ViewHolder(convertView);
            //convertView.setTag(viewHolder);
        } else {
            viewHolder = (RegisteredCustomListAdapter.ViewHolder) convertView.getTag();
        }
        Travel currentItem = (Travel) getItem(position);
         */

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.registered_adapter_row, null);
        }

        TextView textView = (TextView) view.findViewById(R.id.source_registered);//source
        TextView date = view.findViewById(R.id.date_registered);
        TextView destination = view.findViewById(R.id.dest_registered);

        Spinner companySpinner = (Spinner) view.findViewById(R.id.compay_registered);
        Spinner type = view.findViewById(R.id.status_registered);

        textView.setText("מ: " + GPS.getPlace(context, travels.get(position).getPickupAddress()) );
        date.setText("בתאריך" + travels.get(position).getTravelDate() );
        destination.setText("אל: " + GPS.getPlace(context, travels.get(position).getDestAddress()) );


            companySpinnerList.clear();
        companySpinnerList.add(0, "Choose company");
        for (String company : travels.get(position).companyGetter().keySet()){
            companySpinnerList.add(company);

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, this.companySpinnerList);
        companySpinner.setAdapter(adapter);
        companySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int _position, long id) {
                if(lockFirstTimeCompany) {
                    listener.onButtonClicked(position, _position, selectedItemView);
                }

                lockFirstTimeCompany = true;
                /*companyEmail = companySpinnerList.get(_position);*/

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                /*companyEmail = null;*/
            }

        });
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, this.statusSpinner);
        type.setAdapter(adapter1);
        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int _position, long id) {

                 if(lockFirstTimeStatus) {
                    listener.onButtonClicked(position, _position, selectedItemView);
                }


                lockFirstTimeStatus = true;
               /* if (companyEmail != null) {
                    travels.get(position).changeCompanyValue(companyEmail);

                }*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });


        return view;
    }

    /*
    //ViewHolder inner class
    private class ViewHolder {

        TextView source;
        TextView dest;
        TextView date;
        Spinner status;
        Spinner companies;




        public ViewHolder(View view) {

            //source = (TextView)view.findViewById(R.id.source_registered);
            //dest = (TextView)view.findViewById(R.id.dest_registered);
            date = (TextView)view.findViewById(R.id.date_registered);
            status = view.findViewsWithText(R.id.status_registered);

        }

     */

}
