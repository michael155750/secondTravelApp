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

public class HistoryCustomListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Travel> travels;

    public HistoryCustomListAdapter(Context context, ArrayList<Travel> travels) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.history_adapter_row, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Travel currentItem = (Travel) getItem(position);
        String companyEmail = null;
        for (String email: currentItem.getCompany().keySet()){
            if (currentItem.getCompany().get(email))
                companyEmail = email;
        }
        viewHolder.company.setText(currentItem.getCompany().get(companyEmail).toString());
        //viewHolder.way.setText();
        viewHolder.changeStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentItem.setStatus(Travel.RequestType.paid);
            }
        });
        viewHolder.email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send email
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

