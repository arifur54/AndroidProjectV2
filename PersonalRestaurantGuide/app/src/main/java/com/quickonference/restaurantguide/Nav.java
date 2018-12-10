package com.quickonference.restaurantguide;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class Nav extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    TextView txtfName, txtlName;
    StringBuilder uName;
    public static final String DEFAULT = "N/A";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtfName = findViewById(R.id.txtView_fName);
        txtlName = findViewById(R.id.txtView_lName);
        uName = new StringBuilder();
        SharedPreferences shrprefs = getSharedPreferences("Names", Context.MODE_PRIVATE);
        String firName = shrprefs.getString("firstName",DEFAULT);
        String lasName = shrprefs.getString("lastName",DEFAULT);

        Log.d("firstName" ,firName);
        Log.d("lastName" ,lasName);

        if(firName.equals(DEFAULT) || lasName.equals(DEFAULT)){
            txtfName.setText(DEFAULT);
            txtlName.setText(DEFAULT);
        }else{

//            txtfName.setText(firName);
//            txtlName.setText(lasName);
            //fName.setText(lasName);
        }
//



        drawer = findViewById(R.id.nav_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                    new RestaurantListFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_general_schedule);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_general_schedule:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                        new RestaurantListFragment()).commit();
                break;
            case R.id.nav_create_conference:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                        new CreateRestaurantFragment()).commit();
                break;
            case R.id.nav_my_schedule:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                        new my_schedule()).commit();
                break;
            case R.id.nav_twitter:
                Toast.makeText(this, "Twitter", Toast.LENGTH_LONG).show();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        super.onBackPressed();
    }
}
