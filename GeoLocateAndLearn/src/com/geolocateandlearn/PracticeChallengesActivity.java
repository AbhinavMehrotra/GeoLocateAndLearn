package com.geolocateandlearn;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;

import com.geolocateandlearn.data.ChallengeDatabase;
import com.geolocateandlearn.data.PracticeChallengeQuery;
import com.geolocateandlearn.model.Challenge;
import com.geolocateandlearn.model.PracticeChallenge;

public class PracticeChallengesActivity extends ListActivity {

	private CheckBox skillListeningCheckbox;
	private CheckBox skillSpeakingCheckbox;
	private CheckBox skillReadingCheckbox;
	private CheckBox skillWritingCheckbox;

	private ArrayAdapter<Challenge> selectedChallengesAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practice_challenge);
		initSkillCheckboxes();
		initListAdapter();
		initListViewInteractivity();
	}

	private void initListViewInteractivity() {
		final ListView listView = getListView();
		listView.setTextFilterEnabled(true);
		listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parentView,
					View clickedView, int clickedPosition,
					long clickedId) {
				final PracticeChallenge selectedChallenge = (PracticeChallenge) selectedChallengesAdapter
						.getItem(clickedPosition);
				final Intent challengeIntent = new Intent(
						PracticeChallengesActivity.this,
						PerformPracticeChallengeActivity.class);
				challengeIntent
						.putExtra(
								PerformPracticeChallengeActivity.EXTRA_CHALLENGE,
								selectedChallenge);
				startActivity(challengeIntent);
			}
		});
	}

	private void initListAdapter() {
		selectedChallengesAdapter = new ArrayAdapter<Challenge>(this,
				R.layout.list_challenge);
		setListAdapter(selectedChallengesAdapter);
	}

	private void initSkillCheckboxes() {
		skillListeningCheckbox = (CheckBox) findViewById(R.id.listening_skills_selector);
		skillSpeakingCheckbox = (CheckBox) findViewById(R.id.speaking_skills_selector);
		skillReadingCheckbox = (CheckBox) findViewById(R.id.reading_skills_selector);
		skillWritingCheckbox = (CheckBox) findViewById(R.id.writing_skills_selector);

		final SkillCheckboxesChangeListener skillCheckboxesChangeListener = new SkillCheckboxesChangeListener();
		skillListeningCheckbox
				.setOnCheckedChangeListener(skillCheckboxesChangeListener);
		skillSpeakingCheckbox
				.setOnCheckedChangeListener(skillCheckboxesChangeListener);
		skillReadingCheckbox
				.setOnCheckedChangeListener(skillCheckboxesChangeListener);
		skillWritingCheckbox
				.setOnCheckedChangeListener(skillCheckboxesChangeListener);
	}

	private class SkillCheckboxesChangeListener implements
			OnCheckedChangeListener {

		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			final PracticeChallengeQuery myQuery = new PracticeChallengeQuery();
			if (skillListeningCheckbox.isChecked())
				myQuery.requireListening(true);
			if (skillSpeakingCheckbox.isChecked())
				myQuery.requireSpeaking(true);
			if (skillReadingCheckbox.isChecked())
				myQuery.requireReading(true);
			if (skillWritingCheckbox.isChecked())
				myQuery.requireWriting(true);
			final List<Challenge> mySelectedChallenges = ChallengeDatabase
					.getInstance().query(myQuery);
			selectedChallengesAdapter.clear();
			selectedChallengesAdapter.addAll(mySelectedChallenges);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_practice_challenge,
				menu);
		return true;
	}

	public void resetSkills(MenuItem menuItem) {
		skillListeningCheckbox.setChecked(false);
		skillSpeakingCheckbox.setChecked(false);
		skillReadingCheckbox.setChecked(false);
		skillWritingCheckbox.setChecked(false);
	}
}
