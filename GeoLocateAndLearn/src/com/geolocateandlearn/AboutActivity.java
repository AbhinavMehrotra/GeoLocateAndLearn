package com.geolocateandlearn;

import java.util.PriorityQueue;
import java.util.Random;
import java.util.TreeSet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.geolocateandlearn.annotations.ArchitectureSegment;
import com.geolocateandlearn.model.ChallengeRecord;
import com.geolocateandlearn.model.PracticeChallenge;

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

		final String question1 = "Who is buried in the Lincoln Tunnel?";
		final String question2 = "Is Marginal Road important?";
		final String question3 = "Where was the Upside-Down Building?";
		final PracticeChallenge challenge1 = new PracticeChallenge(
				"The About Challenge", question1, question2, question3);
		final ChallengeRecord<PracticeChallenge> record1 = new ChallengeRecord<PracticeChallenge>(
				challenge1);

		class PQE implements Comparable<PQE> {
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

		outputBuilder.append("Constructing values:\n");
		final PriorityQueue<PQE> pqueue = new PriorityQueue<PQE>();
		final Random random = new Random();
		final int upperI = random.nextInt(20);
		for (int newElement = 0; newElement < upperI; newElement++) {
			// Random char in [33, 126]
			final int newTitleInt = random.nextInt(94) + 33;
			final char newTitleChar = Character.toChars(newTitleInt)[0];
			final int newOrder = random.nextInt(50);
			pqueue.add(new PQE(newTitleChar, newOrder));
			outputBuilder.append("   ").append(newTitleChar)
					.append(" ").append(newOrder).append("\n");
		}
		outputBuilder.append("\nRemoving values:\n");

		while (!pqueue.isEmpty()) {
			final PQE outbound = pqueue.remove();
			outputBuilder.append("   ").append(outbound.getTitle())
					.append(" ").append(outbound.getOrder())
					.append("\n");
		}
		outputBuilder.append('\n');

		outputTextView.setText(outputBuilder.toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_about, menu);
		return true;
	}

}
