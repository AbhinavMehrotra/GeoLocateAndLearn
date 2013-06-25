package com.geolocateandlearn.location;

import com.geolocateandlearn.data.AppPreferences;
import com.geolocateandlearn.data.GeoLocationSource;

import android.content.Context;
import android.location.Location;

/**
 * Establishes current location.
 * @author USCHWST
 */
public class Locator {

	private static final String DUMMY_PROVIDER = "DUMMY PROVIDER";

	public static Location getCurrentLocation(Context context) {
		final GeoLocationSource geoLocationSource = AppPreferences
				.getInstance().getGeoLocationSource(context);
		switch (geoLocationSource) {
			case ACTUAL:
				throw new IllegalStateException(
						"Cannot do actual GPS yet");
			case LONDON:
				final Location london = new Location(DUMMY_PROVIDER);
				// TODO set long lat
				return london;
			case NEW_YORK:
				final Location newYork = new Location(DUMMY_PROVIDER);
				// TODO set long lat
				return newYork;
			case SYDNEY:
				final Location sydney = new Location(DUMMY_PROVIDER);
				// TODO set long lat
				return sydney;
			case NONE:
				throw new IllegalStateException(
						"Cannot respond to no provider yet");
		}

		// TODO returning dummy location
		return new Location(DUMMY_PROVIDER);
	}
}
