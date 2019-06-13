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
import android.widget.Toast;

import com.example.smkapk_version1.AddPill.Add_Pill_Activity;
import com.example.smkapk_version1.AddPill.ItemAdapror;
import com.example.smkapk_version1.RoomDatabaseRes.Data;
import com.example.smkapk_version1.RoomDatabaseRes.DataBase;
import com.example.smkapk_version1.RoomDatabaseRes.DataDao;
import com.example.smkapk_version1.RoomDatabaseRes.PillDao;

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
        setContentView(R.layout.pills_menu_activity);

        initLogin();    //Для того щобпрацював перехід зі сповіщення

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

        ItemAdapror adaptor = new ItemAdapror(this );
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

    public void ToastIfWork(View v){
        String message = "This pill ";
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void initLogin() {
        if(LogIn_Activity.currentMail == null || LogIn_Activity.currentName == null || LogIn_Activity.currentSurname == null){
            instance = this;
            database = Room.databaseBuilder(this, DataBase.class, "Data").allowMainThreadQueries().build();

            DataDao dataDao = database.dataDao();
            Data data = dataDao.getByBoolean(true);
            if(data != null) {
                if (data.getEMail() != null && data.getPass() != null) {
                    LogIn_Activity.currentName = data.getFName();
                    LogIn_Activity.currentSurname = data.getSName();
                    LogIn_Activity.currentMail = data.getEMail();
                }
            }
        }
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
