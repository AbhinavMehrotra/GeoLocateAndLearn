package com.geolocateandlearn;

import java.util.Currency;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import com.geolocateandlearn.annotations.ArchitectureSegment;
import com.geolocateandlearn.model.ChallengeRecord;
import com.geolocateandlearn.model.PracticeChallenge;

@ArchitectureSegment(segment = "core")
public class AboutActivity extends Activity {

	private static final String PERIOD = ".";
	private static final String FILE_TO_READ = "lorenipsum.txt";
	private static final Currency currency = Currency.getInstance(Locale.CANADA_FRENCH);
	private static final String currency_string = currency.getSymbol();
	private final static String question1 = "Who is buried in the Lincoln Tunnel?";
	private final static String question2 = "Is Marginal Road important?";
	private final static String question3 = "Where was the Upside-Down Building?";
	private final static PracticeChallenge challenge1 = new PracticeChallenge(
			"The About Challenge", question1, question2, question3);
	private final static ChallengeRecord<PracticeChallenge> challengeRecord1 = new ChallengeRecord<PracticeChallenge>(
			challenge1);

	private static final long ONE_THIRD_SECOND = 333;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		loadOutput();
	}

	final TimerTask incrementorTask = new TimerTask() {

		@Override
		public void run() {
			final Handler handler = new Handler(Looper.getMainLooper());
			handler.post(incrementingRunnable);
		}

	};

	final Runnable incrementingRunnable = new Runnable() {

		public void run() {
			final TextView outputTextView = (TextView) findViewById(R.id.about_screen_textView);
			int value = Integer.parseInt(((String) outputTextView.getText()).substring(1), 16) + 1;
			outputTextView.setText(currency_string + Integer.toHexString(value));
		}

	};

	private void loadOutput() {
		final StringBuilder outputBuilder = new StringBuilder();
		final TextView outputTextView = (TextView) findViewById(R.id.about_screen_textView);
		// TODO Put output here.

		outputTextView.setText(currency_string + "0");

		final Timer mytimer = new Timer(true);
		mytimer.schedule(incrementorTask, ONE_THIRD_SECOND, ONE_THIRD_SECOND);
	}
}
