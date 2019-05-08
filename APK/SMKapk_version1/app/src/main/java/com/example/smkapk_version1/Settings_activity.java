package com.example.smkapk_version1;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.smkapk_version1.MyRes.Data;
import com.example.smkapk_version1.MyRes.DataBase;
import com.example.smkapk_version1.MyRes.DataDao;

public class Settings_activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , View.OnTouchListener{

    ImageView image1, image2, image4, image5, image6, image7, image8, image9, userIcon;
    ConstraintLayout changeUserImageLayout , mainConstraintOfSettings;
    TextView applyImageChange, cancelImageChange;
    Button changeUserImageButton;
    ImageView picToRemove;
    Data d;

    public static Settings_activity instance;
    boolean ChangeLayoutIsOpened = false;
    private DataBase database;
    int isSelected = -1;

    private static final int MAX_CLICK_DURATION = 200;
    private long startClickTime;

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

        changeUserImageButton = (Button) findViewById(R.id.ChangeUserImageButton);
        changeUserImageLayout = (ConstraintLayout) findViewById(R.id.changeUserImageLayout);
        mainConstraintOfSettings = (ConstraintLayout) findViewById(R.id.mainConstraintOfSettings);



        mainConstraintOfSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ChangeLayoutIsOpened) {
                    changeUserImageLayout.setVisibility(View.GONE);
                    ChangeLayoutIsOpened = false;
                }
            }
        });

        changeUserImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ChangeLayoutIsOpened) {
                    ChangeLayoutIsOpened = true;
                    changeUserImageLayout.bringToFront();
                    changeUserImageLayout.invalidate();
                    changeUserImageLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        //previous selectors()
        additionalListeners();

        //----------
        instance = this;
        database = Room.databaseBuilder(this, DataBase.class, "Data").allowMainThreadQueries().build();
        final DataDao loadDao = database.dataDao();
        d = loadDao.getByMail(LogIn_Activity.currentMail);
        //----------

        applyImageChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.setPicNum(isSelected);
                loadDao.update(d);

                updatePicture(isSelected);
                if(picToRemove != null)
                    picToRemove.setBackgroundColor(getResources().getColor(android.R.color.white));

                changeUserImageLayout.setVisibility(View.GONE);
                ChangeLayoutIsOpened = false;
            }
        });

        cancelImageChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.setPicNum(-1);
                loadDao.update(d);

                isSelected = -1;
                if(picToRemove != null)
                    picToRemove.setBackgroundColor(getResources().getColor(android.R.color.white));

                changeUserImageLayout.setVisibility(View.GONE);
                ChangeLayoutIsOpened = false;
            }
        });

        userIcon = (ImageView) findViewById(R.id.SetupUserIcon);
        int choice = d.getPicNum();
        updatePicture(choice);

        TextView Log_Out_Button = (TextView) findViewById(R.id.logOut);
        Log_Out_Button.setOnTouchListener(this);

        Toolbar toolbar = findViewById(R.id.toolbar_settings);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView nameview = findViewById(R.id.NameShowScrollActivity);
        nameview.setText(LogIn_Activity.currentName+" "+LogIn_Activity.currentSurname);       //It is right! remove comment

        //==========
        //PillDao pillDao = database.pillDao();                                   // <-- Remove all !
        //Pill p = pillDao.getById(1);                                            // <-- Remove all !
        //nameview.setText(p.pillName+" "+p.pillCount+" "+p.pillInputDate);       // <-- Remove all !
        //==========

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
            if(!ChangeLayoutIsOpened) {
               Intent i = new Intent(getApplicationContext() , HomePage_Activity.class);
               startActivity(i);
               overridePendingTransition(R.xml.enter_animation, R.xml.exit_animation);
            }
            else {
                changeUserImageLayout.setVisibility(View.GONE);
                ChangeLayoutIsOpened = false;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent inte = new Intent(getApplicationContext() , HomePage_Activity.class);
            inte.putExtra("Number" , -1 );
            startActivity(inte);
        } else if (id == R.id.nav_myPills) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout_settings);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(!ChangeLayoutIsOpened) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    startClickTime = System.currentTimeMillis();
                    break;
                }
                case MotionEvent.ACTION_UP: {
                    long clickDuration = System.currentTimeMillis() - startClickTime;
                    if (clickDuration < MAX_CLICK_DURATION) {
                        final Intent i = new Intent(getApplicationContext(), LogIn_Activity.class);
                        i.putExtra("LogedOut", true);
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
        }
        else
        {
            changeUserImageLayout.setVisibility(View.GONE);
            ChangeLayoutIsOpened = false;
        }
            return true;

    }

    public void additionalListeners() {
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectThisPictureAnimations(image1, 1);
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectThisPictureAnimations(image2, 2);
            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectThisPictureAnimations(image4, 4);
            }
        });

        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectThisPictureAnimations(image5, 5);
            }
        });

        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectThisPictureAnimations(image6, 6);
            }
        });

        image7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectThisPictureAnimations(image7, 7);
            }
        });

        image8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectThisPictureAnimations(image8, 8);
            }
        });

        image9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectThisPictureAnimations(image9, 9);
            }
        });
    }

    public void selectThisPictureAnimations(ImageView thisImage, int thisNum){
        if(isSelected != thisNum) {
            thisImage.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));

            if(picToRemove != null)
                picToRemove.setBackgroundColor(getResources().getColor(android.R.color.white));

            picToRemove = thisImage;
            isSelected = thisNum;
        } else if(isSelected == thisNum) {
            thisImage.setBackgroundColor(getResources().getColor(android.R.color.white));
            picToRemove = null;
            isSelected = -1;
        }
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

    public static Settings_activity getInstance() {
        return instance;
    }

    public DataBase getDatabase() {
        return database;
    }
}