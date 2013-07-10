package com.geolocateandlearn;

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

		final TreeSet<String> questionSet = new TreeSet<String>();

		outputBuilder.append("Before: ");
		outputBuilder.append(questionSet.size());
		outputBuilder.append(" questions.\n");

		questionSet.add(question1);
		questionSet.add(question2);
		questionSet.add(question1);
		questionSet.add(question3);
		questionSet.add(question3);
		questionSet.add(question2);
		questionSet.add(question1);
		questionSet.add(question2);
		questionSet.add(question1);
		questionSet.add(question3);
		questionSet.add(question3);
		questionSet.add(question3);

		outputBuilder.append("After: ");
		outputBuilder.append(questionSet.size());
		outputBuilder.append(" questions.\n");

		outputBuilder.append("First: ");
		outputBuilder.append(questionSet.first());
		outputBuilder.append('\n');

		outputBuilder.append("Last: ");
		outputBuilder.append(questionSet.last());
		outputBuilder.append('\n');

		outputBuilder.append("Head L: ");
		outputBuilder.append(questionSet.headSet("Which"));
		outputBuilder.append('\n');

		outputBuilder.append("Tail, INcluding: ");
		outputBuilder.append(questionSet.tailSet(question3, true));
		outputBuilder.append('\n');

		outputBuilder.append("Tail, EXcluding: ");
		outputBuilder.append(questionSet.tailSet(question3, false));
		outputBuilder.append('\n');

		outputTextView.setText(outputBuilder.toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_about, menu);
		return true;
	}

}
