package com.example.smkapk_version1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.smkapk_version1.MyRes.DataBase;
import com.example.smkapk_version1.MyRes.Pill;

import org.w3c.dom.Text;

public class ItemAdapror extends BaseAdapter {

    LayoutInflater mInflater;
    Pill[] pills;
    Pill onepill;

    public ItemAdapror (Context c , Pill[] p)
    {
        pills = p;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return pills.length;
    }

    @Override
    public Object getItem(int position) {
        return pills[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.details_for_pill , null);

        TextView nameTextView = v.findViewById(R.id.PillNameTextView);
        TextView days_taken = v.findViewById(R.id.DaysOfTakePillDetail);
        TextView today_taken = v.findViewById(R.id.TakesOfPillsTodayDetails);
        TextView descrTextView = v.findViewById(R.id.PillShortDescrTextView);

        Pill pill = pills[position];

       nameTextView.setText(pill.pillName);
       descrTextView.setText("add descr here");
       days_taken.setText("num/num");
       today_taken.setText("num/"+pill.takeRate);


        return v;
    }
}
