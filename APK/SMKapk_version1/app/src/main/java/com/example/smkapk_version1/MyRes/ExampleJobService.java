package com.example.smkapk_version1.MyRes;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import com.example.smkapk_version1.LogIn_Activity;
import com.example.smkapk_version1.Pills_Main_Activity;
import com.example.smkapk_version1.R;

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

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, CHANEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Title")
                .setContentText("Some text...")
                .setContentIntent(contentintent)
                .setAutoCancel(true);

        Notification notification = builder.build();
        notificationManager.notify(1, notification);
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        jobCanceled = true;
        return true;
    }
}
