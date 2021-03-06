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

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

import com.example.secondtravelapp.Models.Travel;
import com.example.secondtravelapp.R;
import com.example.secondtravelapp.services.GPS;


public class RegisteredCustomListAdapter extends BaseAdapter{
    private Context context;

    private ArrayList<String> statusSpinner;
    private ArrayList<Travel> travels;
    private TypeListener typeListener;
    private CompanyListener companyListener;
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

        this.statusSpinner = type;
    }

    public interface TypeListener {
        void onButtonClicked(int position, int spinnerPosition, View view);
    }

    public void setTypeListener(TypeListener listener){
        this.typeListener=listener;
    }

    public interface CompanyListener {
        void onButtonClicked(int position, String email, View view);
    }

    public void setCompanyListener(CompanyListener listener){
        this.companyListener=listener;
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
        date.setText("בתאריך " + travels.get(position).getTravelDate() );
        destination.setText("אל: " + GPS.getPlace(context, travels.get(position).getDestAddress()) );

        ArrayList<String> companySpinnerList = new ArrayList<String>();
        companySpinnerList.clear();
        companySpinnerList.add(0, "Choose company");
        for (String company : travels.get(position).companyGetter().keySet()){
            companySpinnerList.add(company);

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, companySpinnerList);
        companySpinner.setAdapter(adapter);
        companySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int _position, long id) {
                if(lockFirstTimeCompany) {
                    companyEmail = companySpinnerList.get(_position);
                    companyListener.onButtonClicked(position, companyEmail, selectedItemView);
                }


                lockFirstTimeCompany = true;


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
                    typeListener.onButtonClicked(position, _position, selectedItemView);
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



}
