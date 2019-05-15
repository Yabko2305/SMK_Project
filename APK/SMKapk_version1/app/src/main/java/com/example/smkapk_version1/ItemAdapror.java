package com.example.smkapk_version1;

import android.content.Context;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.smkapk_version1.MyRes.DataBase;
import com.example.smkapk_version1.MyRes.Pill;
import com.example.smkapk_version1.MyRes.PillDao;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

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

        Pill pill = pills[position];

        nameTextView.setText(pill.pillName);

        PillDao pillDao = Pills_Main_Activity.getDatabase().pillDao();

        Calendar inputDate = new GregorianCalendar();
        Calendar currentTime = new GregorianCalendar();

        inputDate.setTimeInMillis(pill.startDay);
        inputDate.roll(Calendar.DAY_OF_MONTH, -2);
        int out = currentTime.get(Calendar.DATE) - inputDate.get(Calendar.DATE);

        days_taken.setText(out+"/"+pill.courseLen);
        today_taken.setText(pill.pillsTakenToday+"/"+pill.pillsPerDay);

        return v;
    }
}
