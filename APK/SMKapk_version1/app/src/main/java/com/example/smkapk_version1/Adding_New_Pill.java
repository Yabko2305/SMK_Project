package com.example.smkapk_version1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Adding_New_Pill extends AppCompatActivity {

    TextView showPillName;
    EditText SlotWithPill_AddingPill, Length_of_course_AddingPill, Takes_per_day_AddingPill, Pills_per_take_AddingPill;
    Button Add_new_course_AddingPIll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding__new__pill);
        Intent inte = getIntent();
        String name = inte.getStringExtra("Name");
        showPillName = (TextView) findViewById(R.id.ShowPillName_AddingPill);
        SlotWithPill_AddingPill = (EditText) findViewById(R.id.SlotWithPill_AddingPill);
        Length_of_course_AddingPill = (EditText) findViewById(R.id.Length_of_course_AddingPill);
        Takes_per_day_AddingPill = (EditText) findViewById(R.id.Takes_per_day_AddingPill);
        Pills_per_take_AddingPill = (EditText) findViewById(R.id.Pills_per_take_AddingPill);

        Add_new_course_AddingPIll = (Button) findViewById(R.id.Add_new_course_AddingPIll);

        showPillName.setText(name);

        Add_new_course_AddingPIll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!SlotWithPill_AddingPill.getText().toString().equals("") && !Length_of_course_AddingPill.getText().toString().equals("") &&!Takes_per_day_AddingPill.getText().toString().equals("") &&!Pills_per_take_AddingPill.getText().toString().equals("") )
                {
                   //Add pill in database
                    Intent inte = new Intent(getApplicationContext() , Pills_Main_Activity.class);
                    startActivity(inte);
                    overridePendingTransition(R.xml.enter_animation, R.xml.exit_animation);
                }
                else
                {
                    //Show errors
                }
            }
        });

    }
}