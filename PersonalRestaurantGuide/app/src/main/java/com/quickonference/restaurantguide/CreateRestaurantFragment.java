package com.quickonference.restaurantguide;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import com.quickonference.restaurantguide.conference.Restaurant;


public class CreateRestaurantFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_create_restaurant, container, false);
        getActivity().setTitle("Add a restaurant");
//        private AutoCompleteTextView searchLocation;
//        private PlaceAutocompleteAdapter placeAutocompleteAdapter;
//        private GoogleApiClient mGoogleApiClient;
//        protected GeoDataClient mGeoDataClient;



        Button button =  view.findViewById(R.id.create_rest_btn);
        final EditText restName_txt =  view.findViewById(R.id.create_rest_name);
        final EditText restAddress_txt =  view.findViewById(R.id.create_rest_address);
        final EditText restTag_txt =  view.findViewById(R.id.create_rest_tag);
        final EditText restDetails_txt = view.findViewById(R.id.create_rest_details);
        final RatingBar ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
        final Activity currentActivity = getActivity();
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
                {
                 String restName = restName_txt.getText().toString();
                 String restAddress = restAddress_txt.getText().toString();
                 String restTag = restTag_txt.getText().toString();
                 String restDetails = restDetails_txt.getText().toString();
                 String rating = Float.toString(ratingBar.getRating());
                Restaurant restaurant = new Restaurant(restName, restAddress, restTag, restDetails, rating);
                Restaurant.storeRestaurant(restaurant,currentActivity);
                Toast.makeText(currentActivity, restaurant.getName() + " has been created.", Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new RestaurantListFragment()).commit();
            }
        });
        return view;
    }
}
