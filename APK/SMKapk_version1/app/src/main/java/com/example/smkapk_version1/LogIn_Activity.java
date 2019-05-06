package com.example.smkapk_version1;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Handler;
import com.example.smkapk_version1.MyRes.Data;
import com.example.smkapk_version1.MyRes.DataBase;
import com.example.smkapk_version1.MyRes.DataDao;
import com.example.smkapk_version1.MyRes.Pill;
import com.example.smkapk_version1.MyRes.PillDao;

public class LogIn_Activity extends AppCompatActivity implements View.OnTouchListener {
    public static String currentName, currentSurname , currentMail;
    public static LogIn_Activity instance;
    private DataBase database;

    private static final int MAX_CLICK_DURATION = 200;
    private long startClickTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_layout);

        //----------
        instance = this;
        database = Room.databaseBuilder(this, DataBase.class, "Data").allowMainThreadQueries().build();
        //----------

        checkForUserLogined();

        TextView SignInTextView = findViewById(R.id.SingUpTextView);
        SignInTextView.setOnTouchListener(this);
        Button LogIn = findViewById(R.id.SignUpButton);
        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
        final EditText EmailEditText = findViewById(R.id.EmailEditText);
        final EditText PasswordEditText = findViewById(R.id.PasswordEditText);
        final TextView WrongArguments = findViewById(R.id.WrongArguments);
        WrongArguments.setVisibility(View.INVISIBLE);

        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 0;
                String email = EmailEditText.getText().toString();
                String password = PasswordEditText.getText().toString();

                if(email.length()>0 && password.length()>0) {
                    //----------
                    DataDao dataDao = database.dataDao();
                    //==========
                    PillDao pillDao = database.pillDao();   // <-- Remove all !
                    Pill p = new Pill();                    // <-- Remove all !
                    p.pillName = "Aspirin";                 // <-- Remove all !
                    p.pillCount = 10;                       // <-- Remove all !
                    p.pillInputDate = "11.03.2019";         // <-- Remove all !
                    p.patientMail = email;
                    pillDao.insert(p);
                    //==========
                    Data d = dataDao.getByMail(email);
                    //----------
                    if (d != null && d.getPass().length() > 0)  {
                        if (d.getPass().equals(password)) {
                            if(checkBox.isChecked()){
                                dataDao.changeAllToFalse();
                                d.setRemember(true);
                                dataDao.update(d);
                            }

                            currentName = d.getFName();
                            currentSurname = d.getSName();
                            currentMail = d.getEMail();

                            Intent inte = new Intent(getApplicationContext(), HomePage_Activity.class);
                            inte.putExtra("Number", i);
                            startActivity(inte);
                            i = -1;
                        }
                    }
                }
                if(i!= -1) {
                    WrongArguments.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            WrongArguments.setVisibility(View.INVISIBLE);
                        }
                    }, 2000);
                }
            }
        });


    }

    private void checkForUserLogined() {
        Intent current = getIntent();
        boolean LogedOut = current.getBooleanExtra("LogedOut" , false);

        DataDao loadDao = database.dataDao();
        if(LogedOut) {
            loadDao.changeAllToFalse();
        }

        Data load = loadDao.getByBoolean(true);
        if(load != null){
            if(load.getEMail() != null && load.getPass() != null){
                currentName = load.getFName();
                currentSurname = load.getSName();
                currentMail = load.getEMail();
                if(!LogedOut) {
                    Intent inte = new Intent(getApplicationContext(), HomePage_Activity.class);
                    startActivity(inte);
                }
            }
        }
    }

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
                   Intent i = new Intent(getApplicationContext(), SignUp_Activity.class);
                   startActivity(i);
                   return true;
                   //click event has occurred
               }
           }
       }
       return true;

   }

    public static LogIn_Activity getInstance() {
        return instance;
    }

    public DataBase getDatabase() {
        return database;
    }
}