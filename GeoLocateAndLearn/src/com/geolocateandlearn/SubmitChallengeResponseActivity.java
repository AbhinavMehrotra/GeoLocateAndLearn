package com.geolocateandlearn;

import com.geolocateandlearn.model.ChallengeResponse;

import android.app.Activity;
import android.os.Bundle;

public class SubmitChallengeResponseActivity extends Activity {

	public static final String EXTRA_RESPONSE = "EXTRA_RESPONSE";
	
	private ChallengeResponse response;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_submit_challenge_response);
		response = (ChallengeResponse) getIntent().getSerializableExtra(EXTRA_RESPONSE);
		setTitle(response.getChallenge().getName());
	}
}
