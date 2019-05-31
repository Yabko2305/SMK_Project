package com.example.smkapk_version1.Notifications;

import android.arch.persistence.room.Room;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.smkapk_version1.LogIn_Activity;
import com.example.smkapk_version1.MyRes.Data;
import com.example.smkapk_version1.MyRes.DataBase;
import com.example.smkapk_version1.MyRes.Pill;
import com.example.smkapk_version1.MyRes.PillDao;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class NotificationsReciever extends BroadcastReceiver {
    public static NotificationsReciever instance;
    private DataBase database;

    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra("toastMessage");
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

        //----------
        instance = this;
        database = Room.databaseBuilder(context, DataBase.class, "Data").allowMainThreadQueries().build();
        PillDao pillDao = database.pillDao();
        //----------

        Calendar currentTime = new GregorianCalendar();
        List<Pill> list = pillDao.getAll();
        for(Pill p : list){
            p.lastUse = currentTime.getTimeInMillis();
            pillDao.update(p);
            Log.d("TAAAAAAAAAAAAAAAAAAD", "Updated pill "+p.pillName);
        }
    }

    public static NotificationsReciever getInstance() {
        return instance;
    }

    public DataBase getDatabase() {
        return database;
    }
}
