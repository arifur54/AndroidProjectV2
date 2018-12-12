package com.quickonference.restaurantguide;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static int SP_TIME_OUT=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(MainActivity.this, Nav.class);
                startActivity(i);
                finish();

            }
        }, SP_TIME_OUT);
    }

//    public void toLogIn(View view)
//    {
//        Intent intent = new Intent(this, Login.class);
//        startActivity(intent);
//    }



}
