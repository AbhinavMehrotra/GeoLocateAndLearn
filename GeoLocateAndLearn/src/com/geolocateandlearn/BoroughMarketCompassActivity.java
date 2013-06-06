package com.geolocateandlearn;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class BoroughMarketCompassActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borough_market_compass);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_borough_market_compass, menu);
        return true;
    }
}
