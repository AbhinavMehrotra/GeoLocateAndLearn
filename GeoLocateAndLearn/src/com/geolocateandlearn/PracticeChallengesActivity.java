package com.geolocateandlearn;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;

public class PracticeChallengesActivity extends Activity {

	private CheckBox skillListeningCheckbox;
	private CheckBox skillSpeakingCheckbox;
	private CheckBox skillReadingCheckbox;
	private CheckBox skillWritingCheckbox;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practice_challenge);
		skillListeningCheckbox = (CheckBox) findViewById(R.id.listening_skills_selector);
		skillSpeakingCheckbox = (CheckBox) findViewById(R.id.speaking_skills_selector);
		skillReadingCheckbox = (CheckBox) findViewById(R.id.reading_skills_selector);
		skillWritingCheckbox = (CheckBox) findViewById(R.id.writing_skills_selector);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_practice_challenge, menu);
		return true;
	}

	public void resetSkills(MenuItem menuItem) {
		skillListeningCheckbox.setChecked(false);
		skillSpeakingCheckbox.setChecked(false);
		skillReadingCheckbox.setChecked(false);
		skillWritingCheckbox.setChecked(false);
	}
}
