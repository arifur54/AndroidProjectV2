package com.quickonference.restaurantguide;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TabHost;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.*;
import com.quickonference.restaurantguide.conference.Restaurant;

import android.content.SharedPreferences;
import android.widget.TextView;

public class Tabbed extends Fragment {
//    TabHost tabHost;
//    SharedPreferences confPref;
//    ListView sponsorListView;
//    ListView attendeeListView;
//    private GoogleMap mMap;
//    String [] sponsorList = {"Coca Cola", "Scotia Bank", "RBC", "Ferrari", "Labatt", "LCBO"};
//    String [] addtendeeList = {"James McPherson", "Arun Patel", "Shawn Mendes", "Jospeh Martini", "Mosh Hamadani", "Andrew Suarez", "Aman Singh"};
//    private ArrayAdapter sponsorAdapter;
//    private ArrayAdapter attendeeAdapter;
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        String con_name = getArguments().getString("con_name");
//        getActivity().setTitle(con_name);
//        confPref = this.getActivity().getSharedPreferences("restaurants", Context.MODE_PRIVATE);
//        Gson gson = new Gson();
//        String json = confPref.getString(con_name, "");
//        final Restaurant conference = gson.fromJson(json, Restaurant.class);
//        View view = inflater.inflate(R.layout.fragment_tabbed, container, false);
//        TabHost host = (TabHost) view.findViewById(R.id.tabHost);
//        host.setup();
//
//        //Tab 1
//        TabHost.TabSpec spec = host.newTabSpec("Details");
//        spec.setContent(R.id.tab1);
//        spec.setIndicator("Details");
//        host.addTab(spec);
//        TextView rest_name = (TextView)view.findViewById(R.id.tab_rest_name);
//        rest_name.setText(conference.getName());
//        TextView rest_address = (TextView)view.findViewById(R.id.tab_rest_addres);
//        rest_address.setText(conference.getAddress());
//        TextView rest_tags = (TextView)view.findViewById(R.id.tab_rest_tags);
//        rest_tags.setText(conference.getTag());
//        TextView rest_details = (TextView)view.findViewById(R.id.tab_rest_details);
//        rest_details.setText(conference.getDetails());
//        RatingBar ratingBar = (RatingBar) view.findViewById(R.id.tab_ratingbar);
//        ratingBar.setRating(Float.valueOf(conference.getRating()));
//        //Tab 2
//
////        attendeeListView = (ListView) view.findViewById(R.id.conf_attendees_listview);
////        attendeeAdapter= new ArrayAdapter<>(this.getActivity(), R.layout.listview_item, addtendeeList);
////        attendeeListView.setAdapter(attendeeAdapter);
//        //onMapReady();
//        spec = host.newTabSpec("Map");
//        spec.setContent(R.id.tab2);
//        spec.setIndicator("Map");
//        host.addTab(spec);
//
//        //Tab 3
//        sponsorListView = (ListView) view.findViewById(R.id.conf_sponsors_listview);
//        sponsorAdapter= new ArrayAdapter<>(this.getActivity(), R.layout.listview_item, sponsorList);
//        sponsorListView.setAdapter(sponsorAdapter);
//        spec = host.newTabSpec("Sponsors");
//        spec.setContent(R.id.tab3);
//        spec.setIndicator("Sponsors");
//        host.addTab(spec);
//        return view;
//    }


}
