package com.quickonference.restaurantguide;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText fName, lName;
    Button enter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fName = findViewById(R.id.edText_fName);
        lName = findViewById(R.id.edText_lName);
        enter = findViewById(R.id.btnEnter);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences shrPrefs = getSharedPreferences("Names", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = shrPrefs.edit();

                if(fName.getText().toString().isEmpty() || lName.getText().toString().isEmpty()){
                    Toast.makeText(Login.this, "Field is Empty, Please Enter your First and Last Name", Toast.LENGTH_LONG).show();
                }else {
                    editor.putString("firstName", fName.getText().toString());
                    editor.putString("lastName", lName.getText().toString());
                    editor.commit();
                    Toast.makeText(Login.this, "Data Saved", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Login.this, Nav.class);
                    startActivity(intent);

                }
            }
        });
    }
//    public void toHome(View view)
//    {
//        Intent intent = new Intent(this, Nav.class);
//        startActivity(intent);
//    }
}
