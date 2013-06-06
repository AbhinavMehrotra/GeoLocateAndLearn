package com.geolocateandlearn;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class MeActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_me);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_me, menu);
		return true;
	}

}
