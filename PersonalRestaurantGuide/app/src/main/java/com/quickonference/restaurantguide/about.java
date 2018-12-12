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

public class about extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("About");
        View view = inflater.inflate(R.layout.fragment_my_schedule, container, false);
        return  view;
    }
}
