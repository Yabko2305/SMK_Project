package com.example.smkapk_version1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FAQ_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq_);
    }

    @Override
    public void onBackPressed() {

        Intent inte = new Intent(getApplicationContext() , Settings_activity.class);
        startActivity(inte);
        overridePendingTransition(R.xml.enter_animation, R.xml.exit_animation);

    }
}
