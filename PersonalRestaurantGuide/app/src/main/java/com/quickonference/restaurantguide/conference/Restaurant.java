package com.quickonference.restaurantguide.conference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.*;

public class Restaurant {
    private String name, address, details, tag, rating;
    private Double lat, lng;

    public Restaurant (String name, String address, String tag, String details, String rating, Double lat, Double lng)  {
        this.name = name;
        this.address = address;
        this.tag = tag;
        this.details = details;
        this.rating = rating;
        this.lat = lat;
        this.lng = lng;

    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
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

    public void setName(String name){
        this.name = name;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setTag(String tag){this.tag = tag; }

    public void setDetails(String details){this.details = details; }

    public void setRating(String rating){this.rating = rating; }

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
