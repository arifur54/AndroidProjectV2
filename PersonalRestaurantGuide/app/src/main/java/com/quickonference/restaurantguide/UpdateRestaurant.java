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
        final String res = getIntent.getStringExtra("res");
        final String uData[] = res.split("\n");

        res_Name.setText(uData[0]);
        res_Address.setText(uData[1]);
        res_Tags.setText(uData[2]);
        res_details.setText(uData[3]);
        res_rating.setRating(Float.valueOf(uData[4]));

        rest_Name = res_Name.getText().toString();
        rest_Address = res_Address.getText().toString();
        rest_Tags = res_Tags.getText().toString();
        rest_details = res_details.getText().toString();
        rest_Ratings = Float.toString(res_rating.getRating());

        //Updating the Data
        updatePref = PreferenceManager.getDefaultSharedPreferences(this).edit();
        res_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rest_Name.isEmpty() || rest_Address.isEmpty() || rest_Tags.isEmpty() || rest_details.isEmpty() || rest_Ratings.isEmpty()){
                    Toast.makeText(UpdateRestaurant.this, "Fields Cannot Be Empty", Toast.LENGTH_SHORT).show();
                }
                else {
//                  Restaurant restaurant = new Restaurant(rest_Name, rest_Address, rest_Tags, rest_details, rest_Ratings);
//                  restaurant.storeRestaurant(currActivity,);
//                  updatePref = PreferenceManager.getDefaultSharedPreferences(t).edit();
                    updatePref.putString(uData[0], rest_Name);
                    updatePref.putString(uData[1], rest_Address);
                    updatePref.putString(uData[2], rest_Tags);
                    updatePref.putString(uData[3], rest_details);
                    updatePref.putString(uData[4], rest_Ratings);
                    updatePref.apply();
               }

               // Toast.makeText(UpdateRestaurant.this, "Fields Cannot Be Empty", Toast.LENGTH_SHORT).show();
            }
        });



    }
}
