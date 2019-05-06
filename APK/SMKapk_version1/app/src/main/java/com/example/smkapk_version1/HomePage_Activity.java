package com.example.smkapk_version1;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.smkapk_version1.MyRes.Data;
import com.example.smkapk_version1.MyRes.DataBase;
import com.example.smkapk_version1.MyRes.DataDao;

public class HomePage_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ImageView userIcon;

    public static HomePage_Activity instance;
    private DataBase database;

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

        } else if (id == R.id.nav_slideshow) {

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
