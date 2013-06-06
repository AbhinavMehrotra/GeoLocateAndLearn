package com.geolocateandlearn;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class GeoTrackActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_track);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_geo_track, menu);
        return true;
    }
}
