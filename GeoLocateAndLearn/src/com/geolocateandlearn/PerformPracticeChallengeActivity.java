package com.geolocateandlearn;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.geolocateandlearn.model.PracticeChallenge;

public class PerformPracticeChallengeActivity extends Activity {

	protected static final String EXTRA_CHALLENGE = "EXTRA_CHALLENGE";

	private static PracticeChallenge theChallenge;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_perform_practice_challenge);
		theChallenge = (PracticeChallenge) getIntent().getExtras()
				.getSerializable(EXTRA_CHALLENGE);

		setTitle(theChallenge.getName());
		final TextView question1View = (TextView) findViewById(R.id.question1);
		question1View.setText(theChallenge.getQuestion(1));
		final TextView question2View = (TextView) findViewById(R.id.question2);
		question2View.setText(theChallenge.getQuestion(2));
		final TextView question3View = (TextView) findViewById(R.id.question3);
		question3View.setText(theChallenge.getQuestion(3));
	}
}