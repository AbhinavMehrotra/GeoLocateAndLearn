package com.geolocateandlearn;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.geolocateandlearn.annotations.ArchitectureSegment;
import com.geolocateandlearn.model.ChallengeRecord;
import com.geolocateandlearn.model.PracticeChallenge;

@ArchitectureSegment(segment = "core")
public class AboutActivity extends Activity {

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

	private class PQE implements Comparable<PQE> {
		final private char title;
		final private int order;

		public PQE(char title, int order) {
			this.title = title;
			this.order = order;
		}

		public char getTitle() {
			return title;
		}

		public int getOrder() {
			return order;
		}

		public int compareTo(PQE that) {
			if (this.order == that.order)
				return 0;
			else if (this.order < that.order)
				return -1;
			else
				return 1;
		}

	}

	private void loadOutput() {
		final StringBuilder outputBuilder = new StringBuilder();
		final TextView outputTextView = (TextView) findViewById(R.id.about_screen_textView);
		// TODO Put output here.

		outputBuilder.append("Constructing entries:\n");
		final LinkedHashMap<Integer, PQE> pqueue = new LinkedHashMap<Integer, PQE>();
		final Random random = new Random();
		final int upperI = random.nextInt(20);
		int putCount = 0;
		int overwriteCount = 0;
		for (int newElement = 0; newElement < upperI; newElement++) {
			// Random char in [33, 126]
			final int newTitleInt = random.nextInt(94) + 33;
			final char newTitleChar = Character.toChars(newTitleInt)[0];
			final int newOrder = random.nextInt(50);
			final PQE oldValue = pqueue.put(newOrder, new PQE(
					newTitleChar, newOrder));
			putCount++;
			if (oldValue != null)
				overwriteCount++;
			outputBuilder.append("   ").append(newTitleChar)
					.append(" ").append(newOrder).append("\n");
		}
		outputBuilder.append("\nPuts: ").append(putCount)
				.append("; Overwrites: ").append(overwriteCount)
				.append('\n');
		outputBuilder.append("\nRemoving values:\n");

		int getCount = 0;
		for (Map.Entry<Integer, PQE> whmEntry : pqueue.entrySet()) {
			outputBuilder.append("   ")
					.append(whmEntry.getValue().getTitle()).append(" ")
					.append(whmEntry.getKey()).append(" ")
					.append(whmEntry.getValue().getOrder())
					.append("\n");
			getCount++;
		}
		outputBuilder.append("\nGets: ").append(getCount).append('\n');
		outputBuilder.append('\n');

		outputTextView.setText(outputBuilder.toString());
	}

}
