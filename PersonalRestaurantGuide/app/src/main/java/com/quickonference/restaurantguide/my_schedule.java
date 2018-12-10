package com.quickonference.restaurantguide;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.quickonference.restaurantguide.conference.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class my_schedule extends Fragment {
    ListView LV;
    List<String> conferenceList;
    TextView textView;
    Activity currentActivity;
    private ArrayAdapter listViewAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("My Schedule");
        View view = inflater.inflate(R.layout.fragment_my_schedule, container, false);
        conferenceList = new ArrayList<String>();
        SharedPreferences confPref = this.getActivity().getSharedPreferences("my_conferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        Map<String, ?> allEntries = confPref.getAll();

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            Restaurant conference = gson.fromJson(entry.getValue().toString(), Restaurant.class);
            String conferenceName = conference.getName();
            String CapatitalizedName = conferenceName.substring(0,1).toUpperCase() + conferenceName.substring(1);
            conferenceList.add(CapatitalizedName);
        }
        LV = view.findViewById(R.id.lv_my_schedule);
        listViewAdapter = new ArrayAdapter<>(getActivity(), R.layout.listview_item, conferenceList);
        LV.setAdapter(listViewAdapter);
        LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle args = new Bundle();
                args.putString("con_name", conferenceList.get(position));
                Tabbed tabbed = new Tabbed();
                tabbed.setArguments(args);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, tabbed).commit();
            }
        });
        return  view;
    }
}
