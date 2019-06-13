package com.example.smkapk_version1.Notifications;

import android.app.NotificationManager;
import android.arch.persistence.room.Room;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.smkapk_version1.RoomDatabaseRes.DataBase;
import com.example.smkapk_version1.RoomDatabaseRes.Pill;
import com.example.smkapk_version1.RoomDatabaseRes.PillDao;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class DelayButtonReciever extends BroadcastReceiver {
    public static DelayButtonReciever instance;
    private DataBase database;

    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra("toastMessage");
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

        workWithDB(context);

        //To close notification
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(1);
    }

    private void workWithDB(Context context) {
        instance = this;
        database = Room.databaseBuilder(context, DataBase.class, "Data").allowMainThreadQueries().build();
        PillDao pillDao = database.pillDao();

        Calendar currentTime = new GregorianCalendar();
        List<Pill> list = pillDao.getAll();
        for(Pill p : list){
            p.lastUse = currentTime.getTimeInMillis();
            pillDao.update(p);
        }
    }

    public static DelayButtonReciever getInstance() {
        return instance;
    }

    public DataBase getDatabase() {
        return database;
    }
}
