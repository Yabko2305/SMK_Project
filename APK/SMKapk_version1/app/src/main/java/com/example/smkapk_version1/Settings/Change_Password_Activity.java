package com.example.smkapk_version1.Settings;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smkapk_version1.LogIn_Activity;
import com.example.smkapk_version1.RoomDatabaseRes.Data;
import com.example.smkapk_version1.RoomDatabaseRes.DataBase;
import com.example.smkapk_version1.RoomDatabaseRes.DataDao;
import com.example.smkapk_version1.R;
import com.example.smkapk_version1.Settings_activity;

public class Change_Password_Activity extends AppCompatActivity {

    EditText oldPassword , newPassword , newPasswordRepeat;
    Button ChangePassword;
    TextView wrond ;
    ImageView back;

    //-----
    public static Change_Password_Activity instance;
    private DataBase database;
    Data d;
    DataDao loadDao;
    //-----

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_change_password);


        //----------
        instance = this;
        database = Room.databaseBuilder(this, DataBase.class, "Data").allowMainThreadQueries().build();
        loadDao = database.dataDao();
        d = loadDao.getByMail(LogIn_Activity.currentMail);
        //----------

        oldPassword = (EditText) findViewById(R.id.ChangePassword_OldPassword);
        newPassword = (EditText) findViewById(R.id.ChangePassword_NewPassword);
        newPasswordRepeat = (EditText) findViewById(R.id.ChangePassword_NewPasswordRepeat);

        wrond = (TextView) findViewById(R.id.WrongArguments_ChangePassword);
        wrond.setVisibility(View.GONE);

        back = (ImageView) findViewById(R.id.BackButtonChangePassword);

        ChangePassword = (Button) findViewById(R.id.ChangePasswordButton);

        ChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String old = d.getPass();
                if(!old.equals(oldPassword.getText().toString()))
                {
                    wrond.setVisibility(View.VISIBLE);
                }
                else if(!newPassword.getText().toString().equals(newPasswordRepeat.getText().toString()))
                {
                    wrond.setVisibility(View.VISIBLE);
                }
                else
                {
                    d.setPass(newPassword.getText().toString());
                    loadDao.update(d);
                    Intent inte = new Intent(getApplicationContext() , Settings_activity.class);
                    startActivity(inte);
                    overridePendingTransition(R.xml.enter_animation, R.xml.exit_animation);
                }
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
    public static Change_Password_Activity getInstance() {
        return instance;
    }

    public DataBase getDatabase() {
        return database;
    }
    //-----
}
