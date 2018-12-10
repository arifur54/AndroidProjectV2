package com.quickonference.restaurantguide.conference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.*;

public class Restaurant {
    private String name, address, details, tag, rating;

    public Restaurant (String name, String address, String tag, String details, String rating)  {
        this.name = name;
        this.address = address;
        this.tag = tag;
        this.details = details;
        this.rating = rating;
    }

    public String getName () {return this.name; }
    public String getAddress () {
        return this.address;
    }
    public String getTag () {
        return this.tag;
    }
    public String getDetails () {return this.details;}
    public String getRating () {return this.rating; }

    public static void storeRestaurant (Restaurant con, Activity activity) {
        Gson gson = new Gson();
        String conferenceJson = gson.toJson(con);
        SharedPreferences sharedpref_conference = activity.getSharedPreferences("restaurants", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpref_conference.edit();
        editor.putString(con.name, conferenceJson);
        editor.apply();
    }
    public static void addToSchedule (Restaurant con, Activity activity) {
        Gson gson = new Gson();
        String conferenceJson = gson.toJson(con);
        SharedPreferences sharedpref_conference = activity.getSharedPreferences("restaurants", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpref_conference.edit();
        editor.putString(con.name, conferenceJson);
        editor.apply();
    }
    public static Restaurant getRestaurantFromJson (String json, Activity activity) {
        SharedPreferences sharedpref_conference = activity.getSharedPreferences("restaurants", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        Restaurant conference = gson.fromJson(json, Restaurant.class);
        return conference;
    }
    public static Restaurant getMyRestaurantFromJson (String json, Activity activity) {
        SharedPreferences sharedpref_conference = activity.getSharedPreferences("restaurants", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        Restaurant conference = gson.fromJson(json, Restaurant.class);
        return conference;
    }
}
