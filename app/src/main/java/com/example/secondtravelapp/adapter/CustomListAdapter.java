/*

package com.example.secondtravelapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

import com.example.secondtravelapp.Models.Travel;
import com.example.secondtravelapp.R;

public class CustomListAdapter extends
        BaseAdapter {
    private Context context;
    private ArrayList<Travel> travels;

    public CustomListAdapter(Context context, ArrayList<Travel> travels) {
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
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.travel_adapter_row, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Travel currentItem = (Travel) getItem(position);
        viewHolder.clientName.setText(currentItem.getClientName());
        viewHolder.clientPhone.setText(currentItem.getClientPhone());
        viewHolder.clientEmail.setText(currentItem.getClientEmail());
        */
/*viewHolder.pickupAddress.setText(currentItem.getPickupAddress());
        viewHolder.destAddresses.setText(currentItem.getDestAddresses());
        viewHolder.numOfPassengers.setText(currentItem.getNumOfPassengers());
        viewHolder.status.setText(currentItem.getStatus());
        viewHolder.company.setText(currentItem.getCompany());
        viewHolder.travelDate.setText(currentItem.getTravelDate());
        viewHolder.arrivalDate.setText(currentItem.getArrivalDate());*//*





        return convertView;
    }

    //ViewHolder inner class
    private class ViewHolder {
        TextView clientName;
        TextView clientPhone;
        TextView clientEmail;
        TextView pickupAddress;
        TextView destAddresses;
        TextView numOfPassengers;
        TextView status;
        TextView company;
        TextView travelDate;
        TextView arrivalDate;



        public ViewHolder(View view) {
            clientName = (TextView) view.findViewById(R.id.text_view_item_client_name);
            clientPhone = (TextView)view.findViewById(R.id.text_view_item_client_phone);
            clientEmail = (TextView) view.findViewById(R.id.text_view_item_client_email);
            pickupAddress = (TextView)view.findViewById(R.id.text_view_item_pickup_address);
            destAddresses = (TextView) view.findViewById(R.id.text_view_item_dest_addresses);
            numOfPassengers = (TextView)view.findViewById(R.id.text_view_item_num_passengers);
            status = (TextView) view.findViewById(R.id.text_view_item_status);
            company = (TextView)view.findViewById(R.id.text_view_item_companies);
            travelDate = (TextView) view.findViewById(R.id.text_view_item_travel_date);
            arrivalDate = (TextView)view.findViewById(R.id.text_view_item_arrival_date);

        }
    }
}
*/
