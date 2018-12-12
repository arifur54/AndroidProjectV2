package com.quickonference.restaurantguide;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomeListView extends ArrayAdapter<String> {

    private List<String> restName;
    private List<String> address;
    private List<String> tags;
    private Activity context;

    public CustomeListView(Activity context, List<String> _restName, List<String> _address, List<String> _tags) {
        super(context, R.layout.listview_layout, _restName);

        this.context = context;
        this.restName = _restName;
        this.address = _address;
        this.tags = _tags;
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
        Log.d("kkk", "Hello");
        hv.txtView_restName.setText(restName.get(position));
        hv.txtView_Address.setText(address.get(position));
        hv.txtView_tags.setText(tags.get(position));

        return r;
    }








    static class HoldView {
        TextView txtView_restName, txtView_Address, txtView_tags;

        HoldView(View v) {
            this.txtView_restName = v.findViewById(R.id.txtView_ResName);
            this.txtView_Address = v.findViewById(R.id.txtVIew_Address);
            this.txtView_tags = v.findViewById(R.id.txtView_Tags);

        }
    }
}