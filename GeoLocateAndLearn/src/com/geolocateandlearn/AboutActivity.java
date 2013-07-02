package com.geolocateandlearn;

import com.geolocateandlearn.annotations.ArchitectureSegment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

@ArchitectureSegment(segment = "core")
public class AboutActivity extends Activity {

	Intent intent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_about, menu);
		return true;
	}

}
