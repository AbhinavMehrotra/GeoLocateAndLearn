package com.geolocateandlearn;

import com.geolocateandlearn.annotations.ArchitectureSegment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

@ArchitectureSegment(segment = "core", sequence = 1)
public class MainActivity extends Activity {

	private Intent intent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	// This will be called when about image is clicked
	public void aboutBtn(View v) {
		intent = new Intent(this, AboutActivity.class);
		startActivity(intent);
	}

	/**
	 * This will be called when practice challenges image is clicked
	 * @param v
	 */
	public void practiceChallengesButton(View v) {
		intent = new Intent(this, PracticeChallengesActivity.class);
		startActivity(intent);
	}

	public void nearbyButton(View v) {
		final Intent nearbyIntent = new Intent(this,
				NearbyActivity.class);
		startActivity(nearbyIntent);
	}

	// This will be called when me image is clicked
	public void meBtn(View v) {
		intent = new Intent(this, MeActivity.class);
		startActivity(intent);
	}

	public void editPreferences(MenuItem menuItem) {
		final Intent preferencesIntent = new Intent(this,
				MainPreferencesActivity.class);
		startActivity(preferencesIntent);

	}

	private final OnClickListener onDismissWindow = new OnClickListener() {

		public void onClick(DialogInterface dialog, int which) {
			dialog.cancel();
		}
	};

	public void showCredits(MenuItem menuItem) {
		new AlertDialog.Builder(this)
				.setTitle(getString(R.string.project_team))
				.setMessage(getString(R.string.project_team_names))
				.setIcon(R.drawable.ic_launcher_2)
				.setPositiveButton(R.string.ok, onDismissWindow).show();
	}

	public void showAbout(MenuItem menuItem) {
		new AlertDialog.Builder(this)
				.setTitle(getString(R.string.app_name))
				.setMessage(
						getString(R.string.about_content_alert_dialog))
				.setIcon(R.drawable.ic_launcher_2)
				.setPositiveButton(R.string.ok, onDismissWindow).show();
	}
}
