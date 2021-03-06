package com.quickonference.restaurantguide;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.quickonference.restaurantguide.conference.Restaurant;

public class ViewRestaurantDetails extends AppCompatActivity {

    SharedPreferences restPref;
    Activity currActivity = this;
    TextView setTitle;

    Button btnEdit, btnDelete, btnLoc, btnShare;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_restaurant_details);

        restPref = currActivity.getSharedPreferences("restaurants", Context.MODE_PRIVATE);
        Intent getintent = getIntent();
        final String id = getintent.getStringExtra("id");
        final String sData[] = id.split("\n");
//        Gson gson = new Gson();
//        String json = restPref.getString(id, "");
//        Log.d(json, "kkkk");
//        final Restaurant restaurant = gson.fromJson(json, Restaurant.class);
        Gson gson = new Gson();
        String json = restPref.getString(sData[0], "");
        final Restaurant myRest = gson.fromJson(json, Restaurant.class);
        setTitle = findViewById(R.id.textView_toolBar);
        setTitle.setText(sData[0]);
        TextView rest_name = findViewById(R.id.txtView_EditName);
        rest_name.setText(sData[0]);
        TextView rest_address = findViewById(R.id.txtView_EditAddress);
        rest_address.setText(sData[1]);
        TextView rest_tags = findViewById(R.id.textView_EditTags);
        rest_tags.setText(sData[2]);
        TextView rest_details = findViewById(R.id.txtView_EditDetails);
        rest_details.setText(sData[3]);
        RatingBar ratingBar = findViewById(R.id.edit_RatingBar);
        ratingBar.setRating(Float.valueOf(sData[4]));


        btnLoc = findViewById(R.id.btnMaps);
        btnEdit = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_Delete);
        btnShare = findViewById(R.id.btn_Share);

        // Send to maps Activity
        btnLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(ViewRestaurantDetails.this,MapsAndNav.class);
                Gson gson = new Gson();
                String json = restPref.getString(sData[0], "");
                Restaurant obj = gson.fromJson(json, Restaurant.class);
                Log.d("JSON", json);
                intent.putExtra("r_name", obj.getName());
                intent.putExtra("lat", obj.getLat());
                intent.putExtra("lng", obj.getLng());
                startActivity(intent);
            }
        });

        // send to update Activity
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                String json = restPref.getString(sData[0], "");
                Intent intent = new Intent(ViewRestaurantDetails.this,UpdateRestaurant.class);
                // String uData = sData[0] + "\n" + sData[1]+ "\n" + sData[2] +"\n"+ sData[3] + "\n" + sData[4] + "\n" + sData[5] + "\n" + sData[6];
                intent.putExtra("json", json);
                startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restPref.edit().remove(sData[0]).commit();
                Intent intent = new Intent(ViewRestaurantDetails.this, Nav.class);
                startActivity(intent);
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                String json = restPref.getString(sData[0], "");
                Intent intent = new Intent(ViewRestaurantDetails.this, SendEmail.class);
                // String uData = sData[0] + "\n" + sData[1]+ "\n" + sData[2] +"\n"+ sData[3] + "\n" + sData[4] + "\n" + sData[5] + "\n" + sData[6];
                intent.putExtra("json", json);
                startActivity(intent);
            }
        });
    }



}


