package com.geolocateandlearn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.geolocateandlearn.model.ChallengeResponse;
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

	public void submitAnswers(View v) {
		final ChallengeResponse response = new ChallengeResponse(theChallenge);
		final EditText answer1 = (EditText) findViewById(R.id.answer1);
		response.setAnswer(1, answer1.getText().toString());
		final EditText answer2 = (EditText) findViewById(R.id.answer2);
		response.setAnswer(2, answer2.getText().toString());
		final EditText answer3 = (EditText) findViewById(R.id.answer3);
		response.setAnswer(3, answer3.getText().toString());

		final Intent submitIntent = new Intent(this,
				SubmitChallengeResponseActivity.class);
		startActivity(submitIntent);
	}
}