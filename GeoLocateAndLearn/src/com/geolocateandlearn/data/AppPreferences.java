package com.geolocateandlearn.data;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Singleton.
 * 
 * @author shimon
 * 
 */
public class AppPreferences {
	private static final String NO_EMAIL_ADDRESS = "NO_EMAIL_ADDRESS_STORED@NOMAIL.COM";
	private static final String KEY_EMAIL_ADDRESS = "emailAddress";
	private static final AppPreferences instance = new AppPreferences();

	public static AppPreferences getInstance() {
		return instance;
	}

	private AppPreferences() {

	}

	public String getEmailAddress(final Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getString(KEY_EMAIL_ADDRESS, NO_EMAIL_ADDRESS);
	}

}
