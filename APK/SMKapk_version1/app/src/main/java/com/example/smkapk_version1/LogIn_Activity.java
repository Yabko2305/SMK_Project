package com.example.smkapk_version1;

/*import android.content.Intent;*/
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Handler;

import java.util.ArrayList;


public class LogIn_Activity extends AppCompatActivity implements View.OnTouchListener {
    public static ArrayList<UserClass> users = new ArrayList<UserClass>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_layout);
        TextView SignInTextView = findViewById(R.id.SingUpTextView);
        SignInTextView.setOnTouchListener(this);
        Button LogIn = findViewById(R.id.SignUpButton);
        final EditText EmailEditText = findViewById(R.id.EmailEditText);
        final EditText PasswordEditText = findViewById(R.id.PasswordEditText);
        final TextView WrongArguments = findViewById(R.id.WrongArguments);
        WrongArguments.setVisibility(View.INVISIBLE);
        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = EmailEditText.getText().toString();
                String password = PasswordEditText.getText().toString();
                if(email.toLowerCase().equals("admin"))
                {
                    Intent inte = new Intent(getApplicationContext() , HomePage_Activity.class);
                    inte.putExtra("Number" , -1 );
                    startActivity(inte);
                }
                else {
                    int i = 0;
                    for (UserClass name : users) {
                        if (name.email.equals(email)) {
                            if (name.password.equals(password)) {
                                Intent inte = new Intent(getApplicationContext(), HomePage_Activity.class);
                                inte.putExtra("Number" , i);
                                startActivity(inte);
                                i = -1;
                                break;
                            }
                        }
                        i++;
                    }
                    if(i!= -1)
                    {

                            WrongArguments.setVisibility(View.VISIBLE);
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                WrongArguments.setVisibility(View.INVISIBLE);
                            }
                        }, 2000);


                    }
                }
            }
        });


    }
    private static final int MAX_CLICK_DURATION = 200;
    private long startClickTime;
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

}

/* <Button
        android:id="@+id/button2"
        android:layout_width="347dp"
        android:layout_height="631dp"
        android:layout_marginStart="4dp"
        android:layout_marginTop="4dp"
        android:layout_marginEnd="4dp"
        android:
        android:layout_marginBottom="4dp"
        android:background="@drawable/rounded_background"
        android:clickable="false"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />*/