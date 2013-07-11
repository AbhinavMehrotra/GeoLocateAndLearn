package com.geolocateandlearn;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.geolocateandlearn.annotations.ArchitectureSegment;
import com.geolocateandlearn.model.ChallengeRecord;
import com.geolocateandlearn.model.PracticeChallenge;

@ArchitectureSegment(segment = "core")
public class AboutActivity extends Activity {

	private static final String PERIOD = ".";
	private static final String FILE_TO_READ = "lorenipsum.txt";
	private final static String question1 = "Who is buried in the Lincoln Tunnel?";
	private final static String question2 = "Is Marginal Road important?";
	private final static String question3 = "Where was the Upside-Down Building?";
	private final static PracticeChallenge challenge1 = new PracticeChallenge(
			"The About Challenge", question1, question2, question3);
	private final static ChallengeRecord<PracticeChallenge> challengeRecord1 = new ChallengeRecord<PracticeChallenge>(
			challenge1);

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

		final GregorianCalendar cal = new GregorianCalendar();
		outputBuilder.append("Now: ").append(cal.toString())
				.append("\n\n");

		cal.clear();
		outputBuilder.append("Zero: ").append(cal.toString())
				.append("\n\n");

		for (int i = 0; i < 24; i++) {
			cal.add(Calendar.HOUR, 5);
			cal.add(Calendar.MONTH, 2);
			cal.add(Calendar.MINUTE, 27);
			outputBuilder.append(i).append(": ").append(cal.toString())
					.append("\n\n");
		}

		outputTextView.setText(outputBuilder.toString());
	}
}
