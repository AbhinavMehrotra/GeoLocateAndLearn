package com.geolocateandlearn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class LocationsActivity extends Activity {

	private Intent intent;

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

	// This will be called when London button is clicked
	public void londonBtn(View v) {
		intent = new Intent(this, LondonActivity.class);
		startActivity(intent);
	}

	// This will be called when NewYork button is clicked
	public void newyorkBtn(View v) {
		intent = new Intent(this, NewYorkActivity.class);
		startActivity(intent);
	}

	// This will be called when Sydney button is clicked
	public void sydneyBtn(View v) {
		// do nothing
	}

}
