package com.example.smkapk_version1;

import android.arch.persistence.room.Room;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import com.example.smkapk_version1.MyRes.Data;
import com.example.smkapk_version1.MyRes.DataBase;
import com.example.smkapk_version1.MyRes.DataDao;

public class Change_Name_Activity extends AppCompatActivity {

    EditText newName;
    Button ChangeName;
    ImageView back;

    //-----
    public static Change_Name_Activity instance;
    private DataBase database;
    Data d;
    DataDao loadDao;
    //-----

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change__name_);


        //----------
        instance = this;
        database = Room.databaseBuilder(this, DataBase.class, "Data").allowMainThreadQueries().build();
        loadDao = database.dataDao();
        d = loadDao.getByMail(LogIn_Activity.currentMail);
        //----------

        newName = (EditText) findViewById(R.id.ChangeName_NewName);

        back = (ImageView) findViewById(R.id.BackButtonChangeName);

        ChangeName = (Button) findViewById(R.id.ChangeNameButton);

        ChangeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    d.setFName(newName.getText().toString());
                    loadDao.update(d);

                    Intent inte = new Intent(getApplicationContext() , Settings_activity.class);
                    startActivity(inte);
                    overridePendingTransition(R.xml.enter_animation, R.xml.exit_animation);
                }

        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(getApplicationContext() , Settings_activity.class);
                startActivity(inte);
                overridePendingTransition(R.xml.enter_animation, R.xml.exit_animation);
            }
        });

    }

    @Override
    public void onBackPressed() {

        Intent inte = new Intent(getApplicationContext() , Settings_activity.class);
        startActivity(inte);
        overridePendingTransition(R.xml.enter_animation, R.xml.exit_animation);

    }

    //-----
    public static Change_Name_Activity getInstance() {
        return instance;
    }

    public DataBase getDatabase() {
        return database;
    }
    //-----
}