package com.example.smkapk_version1;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.smkapk_version1.MyRes.Data;
import com.example.smkapk_version1.MyRes.DataBase;
import com.example.smkapk_version1.MyRes.DataDao;
import com.example.smkapk_version1.MyRes.Pill;
import com.example.smkapk_version1.MyRes.PillDao;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ItemAdapror extends BaseAdapter {
    public static ItemAdapror instance;
    private DataBase database;

    TextView nameTextView, days_taken, today_taken;

    LayoutInflater mInflater;
    PillDao pills;

    public ItemAdapror (Context c) {
        //----------
        instance = this;
        database = Room.databaseBuilder(c, DataBase.class, "Data").allowMainThreadQueries().build();
        pills = database.pillDao();
        //----------

        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return pills.getAll().size();
    }

    @Override
    public Object getItem(int position) {
        return pills.getByPosition(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = mInflater.inflate(R.layout.details_for_pill , null);

        List<Pill> list = pills.getAll();
        Pill pill = list.get(position);

        Calendar inputDate = new GregorianCalendar();
        Calendar currentTime = new GregorianCalendar();
        inputDate.setTimeInMillis(pill.startDay);

        int out = currentTime.get(Calendar.DATE) - inputDate.get(Calendar.DATE);
        if(out > pill.courseLen) {
            pills.delete(pill);
        }

        nameTextView = v.findViewById(R.id.PillNameTextView);
        days_taken = v.findViewById(R.id.DaysOfTakePillDetail);
        today_taken = v.findViewById(R.id.TakesOfPillsTodayDetails);

        colorRedIfNotTakenFor_15_Min(pill, currentTime);

        nameTextView.setText(pill.pillName);
        days_taken.setText(out+"/"+(pill.courseLen));
        today_taken.setText(pill.pillsTakenToday+"/"+pill.pillsPerDay);

        return v;
    }

    private void colorRedIfNotTakenFor_15_Min(Pill pill, Calendar currentTime) {
        if(pill.lastUse == 0){
            colorRed();
            return;  //If null
        }

        Calendar lastUsed = new GregorianCalendar();
        lastUsed.setTimeInMillis(pill.lastUse);
        currentTime.roll(Calendar.MINUTE, -15);

        if(currentTime.after(lastUsed))
            colorRed();
    }
    private void colorRed() {
        //nameTextView.setTextColor(Color.RED);
        nameTextView.setBackgroundColor(Color.RED);
    }

    //-----
    public static ItemAdapror getInstance() {
        return instance;
    }

    public DataBase getDatabase() {
        return database;
    }
    //-----
}
