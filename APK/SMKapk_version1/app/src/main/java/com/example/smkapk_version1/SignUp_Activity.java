package com.example.smkapk_version1;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUp_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity_layout);

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
                if(!name.getText().toString().equals("") && !surname.getText().toString().equals("") &&!email.getText().toString().equals("") &&!password.getText().toString().equals("") &&!repeatpassword.getText().toString().equals(""))
                {
                    if(password.getText().toString().equals(repeatpassword.getText().toString()))
                    {
                        UserClass newUser = new UserClass();
                        newUser.name = name.getText().toString();
                        newUser.surname = surname.getText().toString();
                        newUser.email = email.getText().toString();
                        newUser.password = password.getText().toString();
                        LogIn_Activity.users.add(newUser);
                        Intent i = new Intent(getApplicationContext() , LogIn_Activity.class);
                        startActivity(i);
                    }
                    else
                    {
                        wrongPasswords.setVisibility(View.VISIBLE);
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                wrongPasswords.setVisibility(View.INVISIBLE);
                            }
                        }, 2000);
                    }
                }
                else
                {
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
}
