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

	public static final double DUMMY_LONGITUDE_SYDNEY = 151.208727;
	public static final double DUMMY_LONGITUDE_NEWYORK = -74.013225d;
	public static final double DUMMY_LONGITUDE_LONDON = -0.160295d;
	public static final double DUMMY_LATITUDE_SYDNEY = -33.876767;
	public static final double DUMMY_LATITUDE_NEWYORK = 40.704814d;
	public static final double DUMMY_LATITUDE_LONDON = 51.513310d;

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
				london.setLatitude(DUMMY_LATITUDE_LONDON);
				london.setLongitude(DUMMY_LONGITUDE_LONDON);
				return london;
			case NEW_YORK:
				final Location newYork = new Location(DUMMY_PROVIDER);
				newYork.setLatitude(DUMMY_LATITUDE_NEWYORK);
				newYork.setLongitude(DUMMY_LONGITUDE_NEWYORK);
				return newYork;
			case SYDNEY:
				final Location sydney = new Location(DUMMY_PROVIDER);
				sydney.setLatitude(DUMMY_LATITUDE_SYDNEY);
				sydney.setLongitude(DUMMY_LONGITUDE_SYDNEY);
				return sydney;
			case NONE:
			default:
				throw new IllegalStateException(
						"Cannot respond to no provider yet");
		}
	}
}
