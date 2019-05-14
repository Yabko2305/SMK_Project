package com.example.smkapk_version1;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.smkapk_version1.MyRes.Data;
import com.example.smkapk_version1.MyRes.DataBase;
import com.example.smkapk_version1.MyRes.DataDao;
import com.example.smkapk_version1.MyRes.Pill;
import com.example.smkapk_version1.MyRes.PillDao;

import java.util.List;

public class Pills_Main_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView userName;
    ImageView userIcon;
    Data d;
    ListView myListView;
    FloatingActionButton addPill;

    public static Pills_Main_Activity instance;
    boolean ChangeLayoutIsOpened = false;
    private static DataBase database;
    int isSelected = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pills__main_);

        addPill = (FloatingActionButton) findViewById(R.id.AddPill_FromPillsMenu_Button);

        addPill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(getApplicationContext() , Add_Pill_Activity.class);
                startActivity(inte);
            }
        });

        //----------
        instance = this;
        database = Room.databaseBuilder(this, DataBase.class, "Data").allowMainThreadQueries().build();
        final DataDao loadDao = database.dataDao();
        d = loadDao.getByMail(LogIn_Activity.currentMail);
        //----------

        //DEMO_USAGE OF SHOWING PILLS

       myListView = (ListView) findViewById(R.id.PillsListView);
        PillDao pillDao = database.pillDao();
        List<Pill> pills = pillDao.getAll();
        Pill[] Pillarray = new Pill[pills.size()];   //<---- must use this code
        pills.toArray(Pillarray);


       ItemAdapror adaptor = new ItemAdapror(this , Pillarray);
       myListView.setAdapter(adaptor);

        //REFACTOR THIS CODE */

        Toolbar toolbar = findViewById(R.id.toolbar_pills);
        setSupportActionBar(toolbar);
        userName = (TextView) findViewById(R.id.NameShowScrollActivity_PillMenu);
        userIcon = (ImageView) findViewById(R.id.PillMenuUserIcon);

        userName.setText(d.getFName()+" "+d.getSName());
        updatePicture(d.getPicNum());

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        DrawerLayout drawer = findViewById(R.id.drawer_layout_pills);
        NavigationView navigationView = findViewById(R.id.nav_view_pills);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout_pills);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent inte = new Intent(getApplicationContext() , HomePage_Activity.class);
            startActivity(inte);
            overridePendingTransition(R.xml.enter_animation, R.xml.exit_animation);
        }
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
        } else if (id == R.id.nav_myPills) {

        } else if (id == R.id.nav_slideshow) {
            Intent inte = new Intent(getApplicationContext() , Medkit_MainActivity.class);
            startActivity(inte);
        } else if (id == R.id.nav_tools) {
            Intent inte = new Intent(getApplicationContext() , Settings_activity.class);
            startActivity(inte);

        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout_pills);
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

    public static Pills_Main_Activity getInstance() {
        return instance;
    }

    public static DataBase getDatabase() {
        return database;
    }
}
