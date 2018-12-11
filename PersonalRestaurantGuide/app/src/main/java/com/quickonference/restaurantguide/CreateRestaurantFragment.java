package com.quickonference.restaurantguide;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.quickonference.restaurantguide.conference.Restaurant;
import com.quickonference.restaurantguide.helpers.PlaceAutocompleteAdapter;


public class CreateRestaurantFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener {

    private AutoCompleteTextView mSearch;
    private PlaceAutocompleteAdapter placeAutocompleteAdapter;
    GeoDataClient mGeoDataClient;
    private static final LatLngBounds LAT_LNG_BOUNDS = new LatLngBounds(
            new LatLng(-40,-168), new LatLng(49,-74)
    );


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_create_restaurant, container, false);
        getActivity().setTitle("Add a restaurant");
        mGeoDataClient = Places.getGeoDataClient(this.getActivity(), null);
        mSearch = (AutoCompleteTextView) view.findViewById(R.id.edit_rest_address);
        placeAutocompleteAdapter = new PlaceAutocompleteAdapter(this.getActivity(), mGeoDataClient, LAT_LNG_BOUNDS, null);
        mSearch.setAdapter(placeAutocompleteAdapter);


        Button button =  view.findViewById(R.id.create_rest_btn);
        final EditText restName_txt =  view.findViewById(R.id.edit_rest_name);
        final EditText restAddress_txt =  view.findViewById(R.id.edit_rest_address);
        final EditText restTag_txt =  view.findViewById(R.id.edit_rest_tag);
        final EditText restDetails_txt = view.findViewById(R.id.edit_rest_details);
        final RatingBar ratingBar = (RatingBar) view.findViewById(R.id.edit_ratingBar);
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

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
