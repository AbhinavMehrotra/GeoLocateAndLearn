package com.geolocateandlearn;

import com.geolocateandlearn.data.AppPreferences;
import com.geolocateandlearn.model.ChallengeResponse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
		// TODO Auto-generated method stub

	}

	private void setResponseText(final Intent sendEmailIntent) {
		// TODO
		String emailText = "body of email";
		sendEmailIntent.putExtra(Intent.EXTRA_TEXT, emailText);
	}

	private void setResponseSubject(final Intent sendEmailIntent) {
		final StringBuilder emailSubject = new StringBuilder(
				"Challenge response - ");
		final ChallengeResponse response = (ChallengeResponse) getIntent()
				.getSerializableExtra(EXTRA_RESPONSE);
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
