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

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String one = df.format(c);
       char[] arr1 = new char[2];
       arr1[0] = one.charAt(one.length()-2);
       arr1[1] = one.charAt(one.length()-1);
       one = arr1.toString();

       String two = pill.startDay;
       arr1[0] = two.charAt(one.length()-2);
       arr1[1] = two.charAt(one.length()-1);
       two = arr1.toString();
       int oneitn = Integer.parseInt(one);
       int twoint = Integer.parseInt(two);
       int res = oneitn -twoint;

       days_taken.setText(res+"/"+pill.courseLen);
       today_taken.setText(pill.pillsTakenToday+"/"+pill.takeRate);


        return v;
    }
}
