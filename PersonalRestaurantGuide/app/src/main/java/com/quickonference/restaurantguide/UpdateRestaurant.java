package com.quickonference.restaurantguide;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.quickonference.restaurantguide.conference.Restaurant;

public class UpdateRestaurant extends AppCompatActivity {

    EditText res_Name, res_Address, res_Tags, res_details;
    RatingBar res_rating;
    Button res_update;
    SharedPreferences.Editor updatePref;
    String rest_Name, rest_Address, rest_Tags, rest_details, rest_Ratings;
    Activity currActivity = this;
    SharedPreferences sharedpref;
    SharedPreferences.Editor editor;
    Restaurant rest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_restaurant);
        Context ctx = this;
        // Getting and setting the Data
        res_Name = findViewById(R.id.edit_rest_name);
        res_Address = findViewById(R.id.edit_rest_address);
        res_Tags = findViewById(R.id.edit_rest_tag);
        res_details = findViewById(R.id.edit_rest_details);
        res_rating = findViewById(R.id.edit_ratingBar);
        res_update = findViewById(R.id.edit_rest_btn);


        Intent getIntent = getIntent();
//        final String res = getIntent.getStringExtra("res");
//        final String uData[] = res.split("\n");
        String json = getIntent.getStringExtra("json");
        rest = Restaurant.getMyRestaurantFromJson(json, this);
        Log.d("BRUH", rest.toString());


//
//        for(int i =0; i< uData.length;i++){
//            Log.d(uData[i], "kkk");
//        }
//
        res_Name.setText(rest.getName());
        res_Address.setText(rest.getAddress());
        res_Tags.setText(rest.getTag());
        res_details.setText(rest.getDetails());
        res_rating.setRating(Float.valueOf(rest.getRating()));

        //Updating the Data
        sharedpref = this.getSharedPreferences("restaurants", Context.MODE_PRIVATE);
        editor = sharedpref.edit();
        res_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.remove(rest.getName()).apply();
                rest_Name = res_Name.getText().toString();
                rest_Address = res_Address.getText().toString();
                rest_Tags = res_Tags.getText().toString();
                rest_details = res_details.getText().toString();
                rest_Ratings = Float.toString(res_rating.getRating());

                if(rest_Name.isEmpty() || rest_Address.isEmpty() || rest_Tags.isEmpty() || rest_details.isEmpty() || rest_Ratings.isEmpty()){
                    Toast.makeText(UpdateRestaurant.this, "Fields Cannot Be Empty", Toast.LENGTH_SHORT).show();
                }
                else {
                  Restaurant restaurant = new Restaurant(rest_Name, rest_Address, rest_Tags, rest_details, rest_Ratings, rest.getLat(),rest.getLng());
                  Restaurant.storeRestaurant(restaurant,currActivity);
                  Intent intent = new Intent(UpdateRestaurant.this, Nav.class);
                    startActivity(intent);
               }

            }
        });



    }
}
