package com.quickonference.restaurantguide;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.*;
import com.google.gson.*;
import com.quickonference.restaurantguide.conference.Restaurant;

public class RestaurantListFragment extends Fragment {

    View view;
    ListView LV;
    //List<String> conferenceList;
    List<String> resName, address, _tag, desc, rating;
    TextView restName;
    //TextView textView;
    Activity currentActivity;
    CustomeListView customeList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        view = inflater.inflate(R.layout.fragment_general_schedule, container, false);
        getActivity().setTitle("Favourite Restaurant's");
        //conferenceList = new ArrayList<>();
        resName = new ArrayList<>();
        address = new ArrayList<>();
        _tag = new ArrayList<>();
        desc = new ArrayList<>();
        rating = new ArrayList<>();
        SharedPreferences confPref = this.getActivity().getSharedPreferences("restaurants", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        Map<String, ?> allEntries = confPref.getAll();
        currentActivity = getActivity();
        if (allEntries.size() <= 0) {
            View tview = inflater.inflate(R.layout.listview_layout, container, false);
            restName = (TextView) tview.findViewById(R.id.txtView_ResName);
            restName.setText("No conferences found. Create one.");
        } else {
            for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
                Restaurant restaurant = gson.fromJson(entry.getValue().toString(), Restaurant.class);
                resName.add(restaurant.getName());
                address.add(restaurant.getAddress());
                _tag.add(restaurant.getTag());
                desc.add(restaurant.getDetails());
                rating.add(restaurant.getRating());

            }


            LV = view.findViewById(R.id.listView_Restaurents);
            customeList = new CustomeListView(this.getActivity(), resName, address, _tag);
            //listViewAdapter = new ArrayAdapter<>(this.getActivity(), R.layout.listview_item, conferenceList);
            LV.setAdapter(customeList);
            LV.invalidateViews();
            LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Toast.makeText(getActivity(), "Clicked Items: " + position + customeList.getItem(position).toString(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getActivity(), ViewRestaurantDetails.class);
                    String sData = resName.get(position) + "\n" + address.get(position) + "\n" + _tag.get(position) + "\n" + desc.get(position) + "\n" + rating.get(position);
                    intent.putExtra("id", sData);
                    startActivity(intent);
                }
            });
        }
        return view;
    }


}
