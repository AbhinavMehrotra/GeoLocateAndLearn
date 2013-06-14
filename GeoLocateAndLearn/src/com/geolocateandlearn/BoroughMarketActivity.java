package com.geolocateandlearn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.Toast;

public class BoroughMarketActivity extends Activity {

	private String poi,city;
	private ImageView iv;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borough_market);
		Intent intent = getIntent();
		poi = intent.getStringExtra(NearbyActivity.EXTRA_MESSAGE).toLowerCase();
		Toast.makeText(getApplicationContext(), "You have selected "+ poi, Toast.LENGTH_LONG).show();
		iv=(ImageView) findViewById(R.id.imageViewPOI);
		
		if(poi.equalsIgnoreCase("Borough Market")){
			iv.setBackgroundResource(R.drawable.borough);
			city="london";
		}
		else{
			iv.setBackgroundResource(R.drawable.trinity);
			city="newyork";
		}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_borough_market, menu);
        return true;
    }
    
    
}
