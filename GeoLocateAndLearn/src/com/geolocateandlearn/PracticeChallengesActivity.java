package com.geolocateandlearn;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class PracticeChallengesActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practice_challenge);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_practice_challenge, menu);
		return true;
	}

	public void resetSkills(MenuItem menuItem) {
		// TODO reset all skills
	}
}
