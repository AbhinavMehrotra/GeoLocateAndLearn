package com.geolocateandlearn;

import com.geolocateandlearn.data.AppPreferences;
import com.geolocateandlearn.model.ChallengeResponse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

public class SubmitChallengeResponseActivity extends Activity {

	public static final String EXTRA_RESPONSE = "EXTRA_RESPONSE";

	private ChallengeResponse response;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_submit_challenge_response);
		response = (ChallengeResponse) getIntent().getSerializableExtra(
				EXTRA_RESPONSE);
		setTitle(response.getChallenge().getName());
	}

	/*
	 * TODO Implement photography
	 */

	/*
	 * TODO Implement audio recording
	 */

	/**
	 * TODO populate response message with actual data
	 * 
	 * @param v
	 */
	public void sendResponseViaEmail(View v) {
		final Intent sendEmailIntent = new Intent(Intent.ACTION_SEND);
		sendEmailIntent.setType("message/rfc822");
		setResponseRecipient(sendEmailIntent);
		setResponseSubject(sendEmailIntent);
		setResponseText(sendEmailIntent);
		setResponseAttachments(sendEmailIntent);
		try {
			startActivity(Intent.createChooser(sendEmailIntent,
					"Send results..."));
		} catch (android.content.ActivityNotFoundException ex) {
			Toast.makeText(this, "There are no email clients installed.",
					Toast.LENGTH_LONG).show();
		}
	}

	private void setResponseAttachments(Intent sendEmailIntent) {
		// TODO add photograph

		// TODO add audio recording

	}

	private void setResponseText(final Intent sendEmailIntent) {
		final RatingBar ratingFun = (RatingBar) findViewById(R.id.rating_fun);
		final RatingBar ratingEducationValue = (RatingBar) findViewById(R.id.rating_education_value);
		final RatingBar ratingDifficulty = (RatingBar) findViewById(R.id.rating_difficulty);

		final StringBuilder emailText = new StringBuilder("<Answer1>");
		emailText.append(response.getAnswer(1));
		emailText.append("</Answer1>\n<Answer2>");
		emailText.append(response.getAnswer(2));
		emailText.append("</Answer2>\n<Answer3>");
		emailText.append(response.getAnswer(3));
		emailText.append("</Answer3>\n<StarsFun>");
		emailText.append(ratingFun.getRating());
		emailText.append("</StarsFun>\n<StarsEducationValue>");
		emailText.append(ratingEducationValue.getRating());
		emailText.append("</StarsEducationValue>\n<StarsDifficulty>");
		emailText.append(ratingDifficulty.getRating());
		emailText.append("</StarsDifficulty>");
		sendEmailIntent.putExtra(Intent.EXTRA_TEXT, emailText.toString());
	}

	private void setResponseSubject(final Intent sendEmailIntent) {
		final StringBuilder emailSubject = new StringBuilder(
				"Challenge response - ");
		emailSubject.append(response.getChallenge().getName());
		sendEmailIntent.putExtra(Intent.EXTRA_SUBJECT, emailSubject.toString());
	}

	private void setResponseRecipient(final Intent sendEmailIntent) {
		final String emailRecipient = AppPreferences.getInstance()
				.getEmailAddress(this);
		sendEmailIntent.putExtra(Intent.EXTRA_EMAIL,
				new String[] { emailRecipient });
	}
}
