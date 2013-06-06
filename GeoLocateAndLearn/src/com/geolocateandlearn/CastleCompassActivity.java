package com.geolocateandlearn;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CastleCompassActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_castle_compass);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_castle_compass, menu);
        return true;
    }
}
