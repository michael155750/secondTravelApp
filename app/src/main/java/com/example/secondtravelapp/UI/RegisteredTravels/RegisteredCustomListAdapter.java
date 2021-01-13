package com.example.secondtravelapp.UI.RegisteredTravels;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;

import com.example.secondtravelapp.Models.Travel;
import com.example.secondtravelapp.R;
import com.example.secondtravelapp.UI.HistoryTravels.HistoryCustomListAdapter;


public class RegisteredCustomListAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<Travel> travels;

    public RegisteredCustomListAdapter(Context context, ArrayList<Travel> travels) {
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
        RegisteredCustomListAdapter.ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.history_adapter_row, parent, false);
            viewHolder = new RegisteredCustomListAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (RegisteredCustomListAdapter.ViewHolder) convertView.getTag();
        }

        Travel currentItem = (Travel) getItem(position);
        //viewHolder.source.setText();
        //viewHolder.dest.setText();
        viewHolder.date.setText(currentItem.getTravelDate());





        return convertView;
    }

    //ViewHolder inner class
    private class ViewHolder {

        TextView source;
        TextView dest;
        TextView date;
        Spinner status;
        Spinner companies;




        public ViewHolder(View view) {

            source = (TextView)view.findViewById(R.id.source_registered);
            dest = (TextView)view.findViewById(R.id.dest_registered);
            date = (TextView)view.findViewById(R.id.date_registered);

        }
    }
}
