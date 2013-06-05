package com.geolocateandlearn;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class LocationsActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_locations, menu);
        return true;
    }
}
