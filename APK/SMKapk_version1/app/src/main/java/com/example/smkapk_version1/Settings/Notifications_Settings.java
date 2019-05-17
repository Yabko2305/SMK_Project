package com.example.smkapk_version1.Settings;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.smkapk_version1.MyRes.ExampleJobService;
import com.example.smkapk_version1.R;
import com.example.smkapk_version1.Settings_activity;

public class Notifications_Settings extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications__settings);
    }

    @Override
    public void onBackPressed() {
        Intent inte = new Intent(getApplicationContext() , Settings_activity.class);
        startActivity(inte);
        overridePendingTransition(R.xml.enter_animation, R.xml.exit_animation);

    }

    public void scheduleJob(View v) {
        ComponentName componentName = new ComponentName(this, ExampleJobService.class);
        JobInfo info = new JobInfo.Builder(123, componentName)
                .setPersisted(true)
                .setPeriodic(15 * 60 * 1000)
                .build();

        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = scheduler.schedule(info);

        if (resultCode == JobScheduler.RESULT_SUCCESS)
            Log.d(TAG, "Job scheduled");
        else
            Log.d(TAG, "Job scheduling failed");
    }

    public void cancelJob(View v) {
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.cancel(123);
        Log.d(TAG, "Job cancelled");
    }
}
