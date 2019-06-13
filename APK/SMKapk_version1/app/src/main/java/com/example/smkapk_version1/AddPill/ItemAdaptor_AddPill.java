package com.example.smkapk_version1.AddPill;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.smkapk_version1.R;

public class ItemAdaptor_AddPill extends BaseAdapter {
    LayoutInflater mInflater;
    String[] names;

    public ItemAdaptor_AddPill (Context c , String[] p)
    {
        names = p;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int position) {
        return names[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.pills_addmenu_details, null);

        TextView PillName = v.findViewById(R.id.PillName_AddPill);


        PillName.setText(names[position]);


        return v;
    }
}
