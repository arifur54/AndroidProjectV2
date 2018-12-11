package com.quickonference.restaurantguide;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.quickonference.restaurantguide.conference.Restaurant;
import com.quickonference.restaurantguide.helpers.PlaceAutocompleteAdapter;


public class CreateRestaurantFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener {

    private AutoCompleteTextView mSearch;
    private PlaceAutocompleteAdapter placeAutocompleteAdapter;
    GeoDataClient mGeoDataClient;
    private Place mPlace;

    EditText restName_txt, restAddress_txt, restTag_txt, restDetails_txt;
    RatingBar ratingBar;
    Activity currentActivity;

    String restName, restAddress, restTag, restDetails, rating;

    private static final LatLngBounds LAT_LNG_BOUNDS = new LatLngBounds(
            new LatLng(-40,-168), new LatLng(49,-74)
    );
    private GoogleApiClient mGoogleApiClient;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_create_restaurant, container, false);
        getActivity().setTitle("Add a restaurant");
         mGeoDataClient = Places.getGeoDataClient(this.getActivity(), null);
        mGoogleApiClient = new GoogleApiClient
                .Builder(this.getActivity())
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this.getActivity(), this)
                .build();
        mSearch = (AutoCompleteTextView) view.findViewById(R.id.edit_rest_address);
        placeAutocompleteAdapter = new PlaceAutocompleteAdapter(this.getActivity(), mGeoDataClient, LAT_LNG_BOUNDS, null);
        mSearch.setAdapter(placeAutocompleteAdapter);
        mSearch.setOnItemClickListener(mAutoCompleteClickListener);


        Button button =  view.findViewById(R.id.create_rest_btn);
        restName_txt =  view.findViewById(R.id.edit_rest_name);
        restAddress_txt =  view.findViewById(R.id.edit_rest_address);
        restTag_txt =  view.findViewById(R.id.edit_rest_tag);
        restDetails_txt = view.findViewById(R.id.edit_rest_details);
        ratingBar = (RatingBar) view.findViewById(R.id.edit_ratingBar);
        currentActivity = getActivity();

        restName = restName_txt.getText().toString();
        restAddress = restAddress_txt.getText().toString();
        restTag = restTag_txt.getText().toString();
        restDetails = restDetails_txt.getText().toString();
        rating = Float.toString(ratingBar.getRating());
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (restName.isEmpty() || restAddress.isEmpty() || restTag.isEmpty() || restDetails.isEmpty() || rating.isEmpty()) {
                    Toast.makeText(getActivity(), "Field Cannot be emtpy", Toast.LENGTH_SHORT).show();
                } else {

                    Restaurant restaurant = new Restaurant(restName, restAddress, restTag, restDetails, rating);
                    Restaurant.storeRestaurant(restaurant, currentActivity);
                    Toast.makeText(currentActivity, restaurant.getName() + " has been created.", Toast.LENGTH_SHORT).show();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new RestaurantListFragment()).commit();

                }
            }
        });
        return view;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    private AdapterView.OnItemClickListener mAutoCompleteClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            final AutocompletePrediction item = placeAutocompleteAdapter.getItem(position);
            final String placeId = item.getPlaceId();

            PendingResult<PlaceBuffer> placeResults  = Places.GeoDataApi.getPlaceById(mGoogleApiClient,placeId);
            placeResults.setResultCallback(mUpdatePlaceDetailsCallBack);
    };
    };

    private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallBack = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(@NonNull PlaceBuffer places) {
            if (!places.getStatus().isSuccess()) {
                Log.d("THISPLACE", "Place querty did not complete successfully");
                places.release();
            }
            final Place place = places.get(0);
            Log.d("THISPLACE", "Place details" + place.getAttributions());
            Log.d("THISPLACE", "Place details" + place.getLatLng());
            mPlace = place;
            places.release();
        }
    };

}
