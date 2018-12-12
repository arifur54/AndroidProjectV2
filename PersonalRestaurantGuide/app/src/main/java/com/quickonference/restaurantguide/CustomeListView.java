package com.quickonference.restaurantguide;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CustomeListView extends ArrayAdapter<String> implements Filterable {

    private List<String> restDetails;
    private List<String> filteredDetails;
    private Activity context;

    public CustomeListView(Activity context, List<String> restDetails) {
        super(context, R.layout.listview_layout, restDetails);

        this.context = context;
        this.restDetails = restDetails;
        this.filteredDetails = restDetails;

    }
    @Override
    public int getCount()
    {
        return filteredDetails.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View r = convertView;
        CustomeListView.HoldView hv = null;


        if(r==null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.listview_layout,null,true);
            hv = new CustomeListView.HoldView(r);
            r.setTag(hv);
        }else {
            hv = (CustomeListView.HoldView) r.getTag();
        }
        if (filteredDetails != null && filteredDetails.size() > 0 ) {
            String [] restoDetails = getRestoArray(position, filteredDetails);
            hv.txtView_restName.setText(restoDetails[0]);
            hv.txtView_Address.setText(restoDetails[1]);
            hv.txtView_tags.setText(restoDetails[2]);
        }
        return r;
    }


    static class HoldView {
        TextView txtView_restName, txtView_Address, txtView_tags;

        HoldView(View v) {
            this.txtView_restName =v.findViewById(R.id.txtView_ResName);
            this.txtView_Address = v.findViewById(R.id.txtVIew_Address);
            this.txtView_tags = v.findViewById(R.id.txtView_Tags);

        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults results = new FilterResults();

                //If there's nothing to filter on, return the original data for your list
                if(charSequence == null || charSequence.length() == 0)
                {
                    results.values = restDetails;
                    results.count = restDetails.size();
                } else {
                    List<String> restaurentDetails = new ArrayList<>();


                    for(String data : restDetails)
                    {
                        String[] searchArray = data.split("/");
                        String searchString = searchArray[0] + searchArray[2];
                        if (searchString.toLowerCase().contains(charSequence)) {
                            Log.d("fultur", data);
                            restaurentDetails.add(data);
                        }
                    }

                    results.values = restaurentDetails;
                    results.count = restaurentDetails.size();
                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredDetails = (ArrayList<String>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public String[] getRestoArray(int position, List<String> starray) {
        String resto = starray.get(position);
        return resto.split("/");
    }
}