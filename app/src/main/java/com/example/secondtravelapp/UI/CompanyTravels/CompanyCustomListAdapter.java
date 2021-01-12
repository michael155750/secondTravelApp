package com.example.secondtravelapp.UI.CompanyTravels;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import java.util.ArrayList;

import com.example.secondtravelapp.Models.Travel;
import com.example.secondtravelapp.R;
import com.example.secondtravelapp.UI.HistoryTravels.HistoryCustomListAdapter;

public class CompanyCustomListAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<Travel> travels;

    public CompanyCustomListAdapter(Context context, ArrayList<Travel> travels) {
        this.context = context;
        this.travels = travels;
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
        CompanyCustomListAdapter.ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.company_adapter_row, parent, false);
            viewHolder = new CompanyCustomListAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (CompanyCustomListAdapter.ViewHolder) convertView.getTag();
        }

        Travel currentItem = (Travel) getItem(position);
        //viewHolder.source.setText(currentItem.getPickupAddress().);
        //viewHolder.destination.setText();
        viewHolder.numOfPassengers.setText(currentItem.getNumOfPassengers().toString());
        viewHolder.clientName.setText(currentItem.getClientName());
        viewHolder.sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send email
            }
        });
        viewHolder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentItem.setStatus(Travel.RequestType.accepted);
            }
        });

        Long daysNumMiliSec = Math.abs(currentItem.arrivalDateTypeGetter().getTime() - currentItem.travelDateTypeGetter().getTime());
        Long daysNum = (daysNumMiliSec / 1000 / 60 / 60 / 24);
        viewHolder.numOfDays.setText(daysNum.toString());






        return convertView;
    }

    //ViewHolder inner class
    private class ViewHolder {

        TextView source;
        TextView destination;
       TextView numOfPassengers;
       TextView clientName;
       Button sendEmail;
       Button accept;
       TextView numOfDays;
      // DatePicker date;




        public ViewHolder(View view) {

            source = (TextView)view.findViewById(R.id.source);
            destination = (TextView)view.findViewById(R.id.dest);
            numOfPassengers = (TextView)view.findViewById(R.id.num_passengers);
            clientName = (TextView)view.findViewById(R.id.client_name);
            sendEmail = (Button)view.findViewById(R.id.company_send_email);
            accept = (Button)view.findViewById(R.id.accept);
            numOfDays = (TextView)view.findViewById(R.id.num_days);
            //date = (DatePicker)view.findViewById(R.id.)
        }
    }
}
