package com.example.secondtravelapp.UI.HistoryTravels;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

import com.example.secondtravelapp.Models.Travel;
import com.example.secondtravelapp.R;
import com.example.secondtravelapp.UI.RegisteredTravels.RegisteredCustomListAdapter;
import com.example.secondtravelapp.services.GPS;

public class HistoryCustomListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Travel> travels;
    private HistoryCustomListAdapter.HistoryTravelListener listener;

    public HistoryCustomListAdapter(Context context, ArrayList<Travel> travels) {
        this.context = context;
        this.travels = travels;
    }
    public interface HistoryTravelListener {
        void onButtonClicked(int position, View view);
    }

    public void setListener(HistoryCustomListAdapter.HistoryTravelListener listener){
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
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.history_adapter_row, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        /*
        Travel currentItem = (Travel) getItem(position);
        String companyEmail = null;
        for (String email: currentItem.getCompany().keySet()){
            if (currentItem.getCompany().get(email))
                companyEmail = email;
        }

         */


        String companyName = null;
        Boolean bool;
        bool = travels.get(position).getOneCompanyBool("Dan");

        if (bool)
        {
            companyName = "Dan";
        }else{
            bool = travels.get(position).getOneCompanyBool("Metropolin");
            if (bool){
                companyName = "Metropolin";
            }else{
                bool = travels.get(position).getOneCompanyBool("Egged");
                if (bool){
                    companyName = "Egged";
                } else
                {
                    bool = travels.get(position).getOneCompanyBool("Kavim");
                    if (bool){
                        companyName = "Kavim";
                    }
                }
            }
        }
        if (companyName != null){
            viewHolder.company.setText(companyName);
        }

        float distance = GPS.calculateDistance(travels.get(position).getPickupAddress().getLat(),
               travels.get(position).getPickupAddress().getLon(), travels.get(position).getDestAddress().getLat()
               ,travels.get(position).getDestAddress().getLon());
        //viewHolder.way.setText(Float.toString(distance) + " kilometers");



        viewHolder.changeStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onButtonClicked(position, v);
                //travels.get(position).setStatus(Travel.RequestType.paid);
            }
        });
        viewHolder.email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onButtonClicked(position, v);
            }
        });





        return convertView;
    }

    //ViewHolder inner class
    private class ViewHolder {

        TextView company;
        TextView way;
        Button changeStatus;
        Button email;




        public ViewHolder(View view) {

            company = (TextView)view.findViewById(R.id.text_view_item_company_name);
            way = (TextView)view.findViewById(R.id.text_view_item_way);
            changeStatus = (Button)view.findViewById(R.id.change_status_button);
            email = (Button)view.findViewById(R.id.email_button);
        }
    }
}

