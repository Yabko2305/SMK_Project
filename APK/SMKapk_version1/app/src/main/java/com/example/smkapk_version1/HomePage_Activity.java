package com.example.smkapk_version1;

import android.app.DatePickerDialog;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smkapk_version1.MyRes.Data;
import com.example.smkapk_version1.MyRes.DataBase;
import com.example.smkapk_version1.MyRes.DataDao;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomePage_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ImageView userIcon;

    public static HomePage_Activity instance;
    private DataBase database;
    private static final String TAG = "HomepageActivity";

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout_of_slideout_menu);

        //----------
        instance = this;
        database = Room.databaseBuilder(this, DataBase.class, "Data").allowMainThreadQueries().build();
        DataDao loadDao = database.dataDao();
        Data d = loadDao.getByMail(LogIn_Activity.currentMail);
        //----------

        userIcon = (ImageView) findViewById(R.id.MainUserIcon);
        int choice = d.getPicNum();
        updatePicture(choice);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView nameview = findViewById(R.id.NameShowScrollActivity);
        nameview.setText(LogIn_Activity.currentName+" "+LogIn_Activity.currentSurname);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        mDisplayDate = (TextView) findViewById(R.id.dateTextView);

        /*
            The following snippet is causing a crash
            when target label is clicked
            read readme.txt (root project directory) for more info
         */

//        mDisplayDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Calendar cal = Calendar.getInstance();
//                int year = cal.get(Calendar.YEAR);
//                int month = cal.get(Calendar.MONTH);
//                int day = cal.get(Calendar.DAY_OF_MONTH);
//
//                DatePickerDialog dialog = new DatePickerDialog(
//                        HomePage_Activity.this,
//                                android.R.style.Widget_Holo_ActionBar_Solid,
//                                mDateSetListener ,
//                                year, month, day);
//            }
//        });
//
//        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//                Log.d(TAG, "onDateSet: date: " + year + "/" + month + "/" + day);
//            }
//        };



        /*
            Chart drawing example. Mock data.
            TODO *  manage animation opts
                 *  make additional class for chart rendering
                 *  test different chart samples
                 *  rewrite homepage_layout.xml
                 *  make defeat an impossibility in yo mind
        */

        int[][] mockChartData = new int[][] { { 2, 3}, { 3, 5}, {1, 2}, {1, 1}, {8, 5}, {3, 4}, {3, 4}};
        LineChart chart = (LineChart) findViewById(R.id.lineChart_test);

        List<Entry> entries = new ArrayList<Entry>();

        for (int[] item : mockChartData){
            entries.add(new Entry(item[0], item[1]));
        }

        LineDataSet dataSet = new LineDataSet(entries, "Mock data set, no animation ");
        dataSet.setColor(Color.parseColor("#ff9767"));
        dataSet.setValueTextColor(Color.parseColor("#000000"));

        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.invalidate();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_myPills) {

            Intent inte = new Intent(getApplicationContext() , Pills_Main_Activity.class);
            startActivity(inte);

        } else if (id == R.id.nav_slideshow) {
            Intent inte = new Intent(getApplicationContext() , Medkit_MainActivity.class);
            startActivity(inte);
        } else if (id == R.id.nav_tools) {
            Intent inte = new Intent(getApplicationContext() , Settings_activity.class);
            startActivity(inte);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void updatePicture(int isSelected) {
        switch(isSelected) {
            case 1: userIcon.setImageResource(R.drawable.usericons1);
                break;
            case 2: userIcon.setImageResource(R.drawable.usericons2);
                break;
            case 4: userIcon.setImageResource(R.drawable.usericons4);
                break;
            case 5: userIcon.setImageResource(R.drawable.usericons5);
                break;
            case 6: userIcon.setImageResource(R.drawable.usericons6);
                break;
            case 7: userIcon.setImageResource(R.drawable.usericons7);
                break;
            case 8: userIcon.setImageResource(R.drawable.usericons8);
                break;
            case 9: userIcon.setImageResource(R.drawable.usericons9);
                break;
        }
    }

    public static HomePage_Activity getInstance() {
        return instance;
    }

    public DataBase getDatabase() {
        return database;
    }
}
