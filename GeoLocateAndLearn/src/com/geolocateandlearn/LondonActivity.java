package com.geolocateandlearn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class LondonActivity extends Activity {

	private Intent intent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_london);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_london, menu);
		return true;
	}

	// This will be called when borough image is clicked
	public void boroughBtn(View v) {
		// Toast.makeText(getApplicationContext(), "yes",
		// Toast.LENGTH_LONG).show();
		intent = new Intent(this, BoroughMarketActivity.class);
		startActivity(intent);
	}

	// This will be called when borough_compass image is clicked
	public void boroughCompassBtn(View v) {
		intent = new Intent(this, BoroughMarketCompassActivity.class);
		startActivity(intent);
	}

}
