package com.geolocateandlearn;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class BoroughMarketActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borough_market);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_borough_market, menu);
        return true;
    }
}
