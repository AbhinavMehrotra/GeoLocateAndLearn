package com.geolocateandlearn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class NewYorkActivity extends Activity {

	private Intent intent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_york);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_new_york, menu);
		return true;
	}

	// This will be called when castle image is clicked
	public void castleBtn(View v) {
		intent = new Intent(this, CastleActivity.class);
		startActivity(intent);
	}

	// This will be called when castle_compass image is clicked
	public void castleCompassBtn(View v) {
		intent = new Intent(this, CastleCompassActivity.class);
		startActivity(intent);
	}

}
