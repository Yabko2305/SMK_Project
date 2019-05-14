package com.example.smkapk_version1;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smkapk_version1.MyRes.DataBase;
import com.example.smkapk_version1.MyRes.DataDao;
import com.example.smkapk_version1.MyRes.Pill;
import com.example.smkapk_version1.MyRes.PillDao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Adding_New_Pill extends AppCompatActivity {

    TextView showPillName;
    EditText SlotWithPill_AddingPill, Length_of_course_AddingPill, Takes_per_day_AddingPill, Pills_per_take_AddingPill;
    Button Add_new_course_AddingPIll;
    ImageView Back_button;
    public static Adding_New_Pill instance;
    private DataBase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding__new__pill);

        //----------
        instance = this;
        database = Room.databaseBuilder(this, DataBase.class, "Data").allowMainThreadQueries().build();
        //----------

        Intent inte = getIntent();
        final String name = inte.getStringExtra("Name");
        Back_button = (ImageView) findViewById(R.id.BackButtonAddingNewPill);
        showPillName = (TextView) findViewById(R.id.ShowPillName_AddingPill);
        SlotWithPill_AddingPill = (EditText) findViewById(R.id.SlotWithPill_AddingPill);
        Length_of_course_AddingPill = (EditText) findViewById(R.id.Length_of_course_AddingPill);
        Takes_per_day_AddingPill = (EditText) findViewById(R.id.Takes_per_day_AddingPill);
        Pills_per_take_AddingPill = (EditText) findViewById(R.id.Pills_per_take_AddingPill);

        Add_new_course_AddingPIll = (Button) findViewById(R.id.Add_new_course_AddingPIll);

        showPillName.setText(name);

        Back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(getApplicationContext() , Pills_Main_Activity.class);
                startActivity(inte);

            }
        });

        Add_new_course_AddingPIll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!SlotWithPill_AddingPill.getText().toString().equals("") && !Length_of_course_AddingPill.getText().toString().equals("") &&!Takes_per_day_AddingPill.getText().toString().equals("") &&!Pills_per_take_AddingPill.getText().toString().equals("") )
                {

                    DataDao dataDao = database.dataDao();

                    PillDao pillDao = database.pillDao();   // <-- Remove all !
                    Pill p = new Pill();                    // <-- Remove all !
                    p.placeInKit = Integer.parseInt(SlotWithPill_AddingPill.getText().toString());
                    p.courseLen = Integer.parseInt(Length_of_course_AddingPill.getText().toString());
                    p.pillsPerDay = Integer.parseInt(Takes_per_day_AddingPill.getText().toString());
                    //pills per take
                    p.pillName = name;
                    p.patientMail = LogIn_Activity.currentMail;
                    Date c = Calendar.getInstance().getTime();
                    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                    p.pillInputDate = df.format(c);
                    p.pillsPerTake = Integer.parseInt(Pills_per_take_AddingPill.getText().toString());
                    pillDao.insert(p);
                    //==========

                    Intent inte = new Intent(getApplicationContext() , Pills_Main_Activity.class);
                    startActivity(inte);
                    overridePendingTransition(R.xml.enter_animation, R.xml.exit_animation);//
                }
                else
                {
                    //Show errors
                }
            }
        });

    }

    public static Adding_New_Pill getInstance() {
        return instance;
    }

    public DataBase getDatabase() {
        return database;
    }
}