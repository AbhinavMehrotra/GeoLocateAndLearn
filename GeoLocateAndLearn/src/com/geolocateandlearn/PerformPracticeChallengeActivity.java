package com.geolocateandlearn;

import android.app.Activity;
import android.os.Bundle;

public class PerformPracticeChallengeActivity extends Activity {

	protected static final String EXTRA_CHALLENGE = "EXTRA_CHALLENGE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_perform_practice_challenge);
	}
}