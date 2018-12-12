package com.quickonference.restaurantguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.quickonference.restaurantguide.conference.Restaurant;

public class SendEmail extends AppCompatActivity {

    Button btnSend;
    EditText txtTo, txtSub, txtMessage;
    Restaurant rest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);

        txtTo = findViewById(R.id.edText_Email);
        txtSub = findViewById(R.id.edText_subject);
        txtMessage = findViewById(R.id.edText_message);

         btnSend = findViewById(R.id.btnSendEmail);

         Intent getIntent = getIntent();
         String json = getIntent.getStringExtra("json");
         rest = Restaurant.getMyRestaurantFromJson(json, this);
         txtMessage.setText("Restaurant Name: " + rest.getName() + "\n" + "Restaurant Address: " + rest.getAddress() + "\n" +
                 "Tags: " +rest.getTag() + "\n" + "Details: " + rest.getDetails()+ "\n" + "Rating: " + rest.getRating());
         btnSend.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 sendMail();
             }
         });
    }

    private void sendMail(){
        String _toList = txtTo.getText().toString();
        String[] toRec = _toList.split(",");

        String _txtSub = txtSub.getText().toString();
        String _txtMess = txtMessage.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, toRec);
        intent.putExtra(Intent.EXTRA_SUBJECT, _txtSub);
        intent.putExtra(Intent.EXTRA_TEXT, _txtMess);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email Client"));

    }
}
