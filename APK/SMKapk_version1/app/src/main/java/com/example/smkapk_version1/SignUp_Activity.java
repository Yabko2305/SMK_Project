package com.example.smkapk_version1;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.smkapk_version1.RoomDatabaseRes.Data;
import com.example.smkapk_version1.RoomDatabaseRes.DataBase;
import com.example.smkapk_version1.RoomDatabaseRes.DataDao;

public class SignUp_Activity extends AppCompatActivity {
    public static SignUp_Activity instance;
    public DataBase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_signup);

        //----------
        instance = this;
        database = Room.databaseBuilder(this, DataBase.class, "Data").allowMainThreadQueries().build();
        //----------

        final EditText name = findViewById(R.id.nameSignUpEditText);
        final EditText surname = findViewById(R.id.SurnameSignUpEditText);
        final EditText email = findViewById(R.id.EmailSignUpEditText);
        final EditText password = findViewById(R.id.PasswordSignUpEditText);
        final EditText repeatpassword = findViewById(R.id.RepeatPasswordSignUpEditText);
        final TextView wrongArguments = findViewById(R.id.wrongArgumentsSignUp);
        final TextView wrongPasswords = findViewById(R.id.wrongPasswordsSignUp);
        wrongArguments.setVisibility(View.INVISIBLE);
        wrongPasswords.setVisibility(View.INVISIBLE);

        Button signup = findViewById(R.id.SignUpButton);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(!name.getText().toString().equals("") && !surname.getText().toString().equals("") &&!email.getText().toString().equals("") &&!password.getText().toString().equals("") &&!repeatpassword.getText().toString().equals("")) {
                if(password.getText().toString().equals(repeatpassword.getText().toString())) {

                    setUserDataToDB(email.getText().toString(), password.getText().toString(), name.getText().toString(), surname.getText().toString());

                    Intent i = new Intent(getApplicationContext() , LogIn_Activity.class);
                    startActivity(i);
                } else {
                    wrongPasswords.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            wrongPasswords.setVisibility(View.INVISIBLE);
                        }
                    }, 2000);
                }
            } else {
                wrongArguments.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        wrongArguments.setVisibility(View.INVISIBLE);
                    }
                }, 2000);
            }
            }
        });
    }

    private void setUserDataToDB(String email, String password, String name, String surname) {
        DataDao dataDao = database.dataDao();
        Data d = new Data();
        d.setEMail(email);
        d.setPass(password);
        d.setFName(name);
        d.setSName(surname);

        d.setPicNum(0);
        d.setRemember(false);

        dataDao.insert(d);
    }

    public static SignUp_Activity getInstance() {
        return instance;
    }

    public DataBase getDatabase() {
        return database;
    }
}
