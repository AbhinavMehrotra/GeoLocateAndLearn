package com.geolocateandlearn;

import java.util.List;

import android.app.ListActivity;
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

public class PracticeChallengesActivity extends ListActivity {

	private CheckBox skillListeningCheckbox;
	private CheckBox skillSpeakingCheckbox;
	private CheckBox skillReadingCheckbox;
	private CheckBox skillWritingCheckbox;

	// private final List<Challenge> selectedChallengesList = new
	// ArrayList<Challenge>();

	private ArrayAdapter<Challenge> selectedChallengesAdapter;

	// private final ChallengeListAdapter selectedChallengeListAdapter = new
	// ChallengeListAdapter(
	// null, selectedChallengesList);
	// private final DataSetObserver MyChallengeListObserver = new
	// DataSetObserver() {
	// };

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

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void initListAdapter() {
		selectedChallengesAdapter = new ArrayAdapter<Challenge>(this,
				R.layout.list_challenge);
		setListAdapter(selectedChallengesAdapter);
	}

	@Override
	protected void onResume() {
		super.onResume();
		// selectedChallengeListAdapter.registerDataSetObserver(this);
	}

	@Override
	protected void onPause() {
		// selectedChallengeListAdapter.unregisterDataSetObserver(this);
		super.onPause();
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
