package com.geolocateandlearn;

import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import com.geolocateandlearn.annotations.ArchitectureSegment;
import com.geolocateandlearn.model.Challenge;
import com.geolocateandlearn.model.ChallengeRecord;
import com.geolocateandlearn.model.PracticeChallenge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

@ArchitectureSegment(segment = "core")
public class AboutActivity extends Activity {

	Intent intent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		loadOutput();
	}

	private void loadOutput() {
		final StringBuilder outputBuilder = new StringBuilder();
		final TextView outputTextView = (TextView) findViewById(R.id.about_screen_textView);
		// TODO Put output here.
		final PracticeChallenge challenge1 = new PracticeChallenge(
				"The About Challenge",
				"Who is buried in the Lincoln Tunnel",
				"Is Marginal Road important?",
				"Where was the Upside-Down Building?");
		final ChallengeRecord<PracticeChallenge, Float> record1 = new ChallengeRecord<PracticeChallenge, Float>(
				challenge1, new ConcurrentHashMap<Long, TreeSet<Byte>>());
		outputBuilder.append(challenge1);
		outputBuilder.append('\n');
		outputBuilder.append(record1);

		outputTextView.setText(outputBuilder.toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_about, menu);
		return true;
	}

}
