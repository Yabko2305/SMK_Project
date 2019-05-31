package com.example.smkapk_version1.Notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import com.example.smkapk_version1.LogIn_Activity;
import com.example.smkapk_version1.MyRes.DataBase;
import com.example.smkapk_version1.MyRes.Pill;
import com.example.smkapk_version1.MyRes.PillDao;
import com.example.smkapk_version1.Notifications.NotificationsReciever;
import com.example.smkapk_version1.Pills_Main_Activity;
import com.example.smkapk_version1.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class ExampleJobService extends JobService {
    public static final String TAG = "ExampleJobService";
    private boolean jobCanceled = false;

    @Override
    public boolean onStartJob(JobParameters params) {
        doBackgroundWork(params);
        return true;
    }

    private void doBackgroundWork(final JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(jobCanceled) return;

                createNotification();

                jobFinished(params, false);
            }
        }).start();
    }

    private void createNotification() {
        String CHANEL_ID = "1";
        String pillsToTake = getPills();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANEL_ID,
                    "My chanel", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Description");
            channel.enableLights(true);
            channel.enableVibration(true);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);

            notificationManager.createNotificationChannel(channel);
        }

        Intent activityIntent = new Intent(this, Pills_Main_Activity.class);
        PendingIntent contentintent = PendingIntent.getActivity(this, 0, activityIntent, 0);

        Intent broadkastIntent = new Intent(this, NotificationsReciever.class);
        broadkastIntent.putExtra("toastMessage", "Take pill in 15 minutes!");
        PendingIntent actionIntent = PendingIntent.getBroadcast(this, 0, broadkastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, CHANEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Reminder")
                .setContentText(pillsToTake)
                .setContentIntent(contentintent)
                .setAutoCancel(true)
                .addAction(R.mipmap.ic_launcher, "Delay", actionIntent);

        Notification notification = builder.build();
        notificationManager.notify(1, notification);

        PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
        boolean isScreenOn = Build.VERSION.SDK_INT >= 20 ? pm.isInteractive() : pm.isScreenOn();

        if(!isScreenOn){
            PowerManager.WakeLock wl =  pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "myApp:notificationLock");
            wl.acquire(3000);
        }
    }

    private String getPills(){
        Calendar time = new GregorianCalendar();
        time.roll(Calendar.MINUTE, -15);
        Calendar pillTakeTime = new GregorianCalendar();

        DataBase dataBase = Room.databaseBuilder(this, DataBase.class, "Data").allowMainThreadQueries().build();
        PillDao pillDao = dataBase.pillDao();

        List<Pill> list = pillDao.getAll();
        List<String> out = new ArrayList<String>();
        for(Pill p : list){
            pillTakeTime.setTimeInMillis(p.lastUse);
            if(time.after(pillTakeTime)){   //May be changed
                out.add(p.pillName);
            }
        }
        if (out.size() == 0) return "No pills to take";
        return out.toString();
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        jobCanceled = true;
        return true;
    }
}
