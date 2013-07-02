package com.geolocateandlearn;

import com.geolocateandlearn.annotations.ArchitectureSegment;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

@ArchitectureSegment(segment = "core")
public class MainPreferencesActivity extends PreferenceActivity {

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getFragmentManager()
				.beginTransaction()
				.replace(android.R.id.content,
						new MyPreferenceFragment()).commit();
	}

	public static class MyPreferenceFragment extends PreferenceFragment {
		@Override
		public void onCreate(final Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.preferences);
		}
	}

}
