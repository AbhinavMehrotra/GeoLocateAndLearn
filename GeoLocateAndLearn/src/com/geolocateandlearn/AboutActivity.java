package com.geolocateandlearn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.TextView;

import com.geolocateandlearn.annotations.ArchitectureSegment;
import com.geolocateandlearn.model.ChallengeRecord;
import com.geolocateandlearn.model.PracticeChallenge;

@ArchitectureSegment(segment = "core")
public class AboutActivity extends Activity {

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

		final StringBuilder fileContents = new StringBuilder();
		FileReader fileReader = null;
		InputStream is = null;
		BufferedReader bufferedReader = null;
		try {
			final AssetManager am = this.getAssets();
			is = am.open(FILE_TO_READ);
			bufferedReader = new BufferedReader(new InputStreamReader(
					is));
			String lineFromFile;
			try {
				while ((lineFromFile = bufferedReader.readLine()) != null) {
					fileContents.append(lineFromFile).append('\n');
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (fileReader != null)
				try {
					fileReader.close();
				} catch (IOException e) {
					// noop
				}
		}

		outputBuilder.append("Read ").append(fileContents.length())
				.append(" characters.");

		final StringTokenizer tokenizer = new StringTokenizer(" .");

		outputTextView.setText(outputBuilder.toString());
	}
}
