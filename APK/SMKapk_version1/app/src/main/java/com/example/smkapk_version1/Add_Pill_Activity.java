package com.example.smkapk_version1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;

public class Add_Pill_Activity extends AppCompatActivity {
    ListView myListView;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__pill_);

        Toolbar toolbar = findViewById(R.id.toolbar_addpill);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        back = (ImageView) findViewById(R.id.BackButtonAddPill);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(getApplicationContext() , Pills_Main_Activity.class);
                startActivity(inte);
                overridePendingTransition(R.xml.enter_animation, R.xml.exit_animation);
            }
        });

        myListView = (ListView) findViewById(R.id.ListView_AddPill);
        final String[] names = getResources().getStringArray(R.array.PillNames);
       ItemAdaptor_AddPill adaptor = new ItemAdaptor_AddPill(this , names);
        myListView.setAdapter(adaptor);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent inte = new Intent(getApplicationContext() , Adding_New_Pill.class);
                inte.putExtra("Name" , names[position]);
                startActivity(inte);

            }
        });
    }

    @Override
    public void onBackPressed() {

        Intent inte = new Intent(getApplicationContext() , Pills_Main_Activity.class);
        startActivity(inte);
        overridePendingTransition(R.xml.enter_animation, R.xml.exit_animation);

    }

}
