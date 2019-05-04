package com.example.smkapk_version1;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smkapk_version1.MyRes.Data;
import com.example.smkapk_version1.MyRes.DataBase;
import com.example.smkapk_version1.MyRes.DataDao;


public class Settings_activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , View.OnTouchListener{

    //-----
    public static Settings_activity instance;
    private DataBase database;
    //-----

    int isSelected = -1;
    ImageView image1;
    ImageView image2;
    ImageView image4;
    ImageView image5;
    ImageView image6;
    ImageView image7;
    ImageView image8;
    ImageView image9;
    TextView applyImageChange;
    TextView cancelImageChange;
    Data d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_activity);

        applyImageChange = (TextView) findViewById(R.id.ApplyChangeImage);
        cancelImageChange = (TextView) findViewById(R.id.CancelChangeImage);

        image1 = (ImageView) findViewById(R.id.usericon1);
        image2 = (ImageView) findViewById(R.id.usericon2);
        image4 = (ImageView) findViewById(R.id.usericon4);
        image5 = (ImageView) findViewById(R.id.usericon5);
        image6 = (ImageView) findViewById(R.id.usericon6);
        image7 = (ImageView) findViewById(R.id.usericon7);
        image8 = (ImageView) findViewById(R.id.usericon8);
        image9 = (ImageView) findViewById(R.id.usericon9);
        //3d is deleted

        selectors();

        //----------
        instance = this;
        database = Room.databaseBuilder(this, DataBase.class, "Data").allowMainThreadQueries().build();
        DataDao loadDao = database.dataDao();
        d = loadDao.getByMail(LogIn_Activity.currentMail);
        //----------

        applyImageChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.setPicNum(isSelected);
            }
        });

        TextView Log_Out_Button = (TextView) findViewById(R.id.logOut);
        Log_Out_Button.setOnTouchListener(this);

        Toolbar toolbar = findViewById(R.id.toolbar_settings);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView nameview = findViewById(R.id.NameShowScrollActivity);
        nameview.setText(LogIn_Activity.currentName+" "+LogIn_Activity.currentSurname);

        DrawerLayout drawer = findViewById(R.id.drawer_layout_settings);
        NavigationView navigationView = findViewById(R.id.nav_view_settings);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout_settings);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent inte = new Intent(getApplicationContext() , HomePage_Activity.class);
            inte.putExtra("Number" , -1 );
            startActivity(inte);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {
            //do nothing
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout_settings);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private static final int MAX_CLICK_DURATION = 200;
    private long startClickTime;
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                startClickTime = System.currentTimeMillis();
                break;
            }
            case MotionEvent.ACTION_UP: {
                long clickDuration = System.currentTimeMillis() - startClickTime;
                if (clickDuration < MAX_CLICK_DURATION) {
                   final Intent i = new Intent(getApplicationContext(), LogIn_Activity.class);
                    i.putExtra("LogedOut" , true);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            startActivity(i);
                        }
                    }, 50);

                    return true;
                    //click event has occurred
                }
            }
        }
        return true;
    }

    public void removePic(int pic)
    {
        switch(pic)
        {
            case 1: image1.setBackgroundColor(getResources().getColor(android.R.color.white));
            break;
            case 2: image2.setBackgroundColor(getResources().getColor(android.R.color.white));
            break;
            case 4: image4.setBackgroundColor(getResources().getColor(android.R.color.white));
            break;
            case 5: image5.setBackgroundColor(getResources().getColor(android.R.color.white));
            break;
            case 6: image6.setBackgroundColor(getResources().getColor(android.R.color.white));
            break;
            case 7: image7.setBackgroundColor(getResources().getColor(android.R.color.white));
            break;
            case 8: image8.setBackgroundColor(getResources().getColor(android.R.color.white));
            break;
            case 9: image9.setBackgroundColor(getResources().getColor(android.R.color.white));
            break;
        }
    }

    public void selectors()
    {
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSelected == -1)
                {
                    image1.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    isSelected = 1;

                }
                else if(isSelected == 1)
                {
                    image1.setBackgroundColor(getResources().getColor(android.R.color.white));
                    isSelected = -1;
                }
                else
                {
                    removePic(isSelected);
                    isSelected = -1;
                }
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSelected == -1)
                {
                    image2.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    isSelected = 2;

                }
                else if(isSelected == 2)
                {
                    image2.setBackgroundColor(getResources().getColor(android.R.color.white));
                    isSelected = -1;
                }
                else
                {
                    removePic(isSelected);
                    isSelected = -1;
                }
            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSelected == -1)
                {
                    image4.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    isSelected = 4;

                }
                else if(isSelected == 4)
                {
                    image4.setBackgroundColor(getResources().getColor(android.R.color.white));
                    isSelected = -1;
                }
                else
                {
                    removePic(isSelected);
                    isSelected = -1;
                }
            }
        });

        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSelected == -1)
                {
                    image5.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    isSelected = 5;

                }
                else if(isSelected == 5)
                {
                    image5.setBackgroundColor(getResources().getColor(android.R.color.white));
                    isSelected = -1;
                }
                else
                {
                    removePic(isSelected);
                    isSelected = -1;
                }
            }
        });

        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSelected == -1)
                {
                    image6.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    isSelected = 6;

                }
                else if(isSelected == 6)
                {
                    image6.setBackgroundColor(getResources().getColor(android.R.color.white));
                    isSelected = -1;
                }
                else
                {
                    removePic(isSelected);
                    isSelected = -1;
                }
            }
        });

        image7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSelected == -1)
                {
                    image7.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    isSelected = 7;

                }
                else if(isSelected == 7)
                {
                    image7.setBackgroundColor(getResources().getColor(android.R.color.white));
                    isSelected = -1;
                }
                else
                {
                    removePic(isSelected);
                    isSelected = -1;
                }
            }
        });

        image8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSelected == -1)
                {
                    image8.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    isSelected = 8;

                }
                else if(isSelected == 8)
                {
                    image8.setBackgroundColor(getResources().getColor(android.R.color.white));
                    isSelected = -1;
                }
                else
                {
                    removePic(isSelected);
                    isSelected = -1;
                }
            }
        });

        image9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSelected == -1)
                {
                    image9.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    isSelected = 9;

                }
                else if(isSelected == 9)
                {
                    image9.setBackgroundColor(getResources().getColor(android.R.color.white));
                    isSelected = -1;
                }
                else
                {
                    removePic(isSelected);
                    isSelected = -1;
                }
            }
        });

    }

    //-----
    public static Settings_activity getInstance() {
        return instance;
    }

    public DataBase getDatabase() {
        return database;
    }
    //-----

}
