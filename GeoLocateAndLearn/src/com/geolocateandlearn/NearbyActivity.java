package com.geolocateandlearn;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Locale;

import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.geolocateandlearn.annotations.ArchitectureSegment;
import com.geolocateandlearn.location.Locator;
import com.geolocateandlearn.model.PointOfInterest;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

@ArchitectureSegment(segment = "nearby", sequence = 1)
public class NearbyActivity extends FragmentActivity {

	public static final String EXTRA_MESSAGE = "com.geolocateandlearn.NearbyActivity";
	private GoogleMap mMap;
	private LocationManager lm;
	private LocationListener ll;
	private Location loc;
	private ProgressBar pb;
	private String city;
	private ArrayList<String> poiLondon = new ArrayList<String>();
	private ArrayList<String> poiNY = new ArrayList<String>();

	private ArrayAdapter<PointOfInterest> poiAdapter;
	private Location currentLocation;

	// private LinearLayout linear;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nearby);
		createPoiList();

		// TODO

		// linear = (LinearLayout)findViewById(R.id.linear);
		// poiLondon.add("Borough Market");
		// poiLondon.add("Monument");
		// poiLondon.add("The George Inn");
		Toast.makeText(getApplicationContext(),
				"Getting your location.", Toast.LENGTH_LONG).show();
		pb = (ProgressBar) findViewById(R.id.progressBar);
		pb.setVisibility(ProgressBar.VISIBLE);

		lm = (LocationManager) getApplicationContext()
				.getSystemService(LOCATION_SERVICE);
		ll = new LocationListener() {

			public void onStatusChanged(String provider, int status,
					Bundle extras) {
				// TODO Auto-generated method stub

			}

			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub

			}

			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub

			}

			public void onLocationChanged(Location location) {
				lm.removeUpdates(ll);
				pb.setVisibility(ProgressBar.INVISIBLE);
				loc = location;
				setUpMapIfNeeded(loc);
				city = getNearestCity(loc).toUpperCase(Locale.US);
				Toast.makeText(getApplicationContext(),
						"You are near " + city, Toast.LENGTH_LONG)
						.show();
				displayPOI(city);

			}
		};
		String provider = lm.getBestProvider(new Criteria(), true);
		lm.requestLocationUpdates(provider, 10000, 0, ll);

		postOutput();
	}

	private void createPoiList() {
		final ListView poiListView = (ListView) findViewById(R.id.poi_listview);
		poiAdapter = new ArrayAdapter<PointOfInterest>(this,
				R.layout.point_of_interest);
		poiListView.setAdapter(poiAdapter);
	}

	@Override
	protected void onStart() {
		super.onStart();
		currentLocation = Locator.getCurrentLocation(this);
		reloadPointsOfInterest();
	}

	private void reloadPointsOfInterest() {
		poiAdapter.clear();
		if (currentLocation.getLatitude() == Locator.DUMMY_LATITUDE_LONDON
				&& currentLocation.getLongitude() == Locator.DUMMY_LONGITUDE_LONDON) {
			poiAdapter.add(new PointOfInterest("London 1"));
			poiAdapter.add(new PointOfInterest("london 2"));
			poiAdapter.add(new PointOfInterest("london 3"));
		} else if (currentLocation.getLatitude() == Locator.DUMMY_LATITUDE_NEWYORK
				&& currentLocation.getLongitude() == Locator.DUMMY_LONGITUDE_NEWYORK) {
			poiAdapter.add(new PointOfInterest("9/11 Exhibit"));
			poiAdapter.add(new PointOfInterest("Fraunces Tavern"));
			poiAdapter.add(new PointOfInterest("Skyscraper Museum"));
		}
		if (currentLocation.getLatitude() == Locator.DUMMY_LATITUDE_SYDNEY
				&& currentLocation.getLongitude() == Locator.DUMMY_LONGITUDE_SYDNEY) {
			poiAdapter.add(new PointOfInterest("sydney 1"));
			poiAdapter.add(new PointOfInterest("sydney 2"));
			poiAdapter.add(new PointOfInterest("sydney 3"));
		}
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void onResume() {
		super.onResume();
		// String provider = lm.getBestProvider(new Criteria(), true);
		// lm.requestLocationUpdates(provider, 10000, 0, ll);
	}

	private void setUpMapIfNeeded(Location loc) {
		// Do a null check to confirm that we have not already instantiated the
		// map.
		if (mMap == null) {
			// Try to obtain the map from the SupportMapFragment.
			mMap = ((SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map)).getMap();
			// Check if we were successful in obtaining the map.
			if (mMap != null) {
				try {
					MapsInitializer.initialize(this);
					Log.e("GeoTager", "Initialized");
					mMap.setMyLocationEnabled(true);
					// Location l = mMap.getMyLocation();
					LatLng current = new LatLng(loc.getLatitude(),
							loc.getLongitude());
					CameraUpdate center = CameraUpdateFactory
							.newLatLng(current);
					CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
					mMap.addMarker(new MarkerOptions()
							.icon(BitmapDescriptorFactory
									.defaultMarker(BitmapDescriptorFactory.HUE_RED))
							.position(current));
					mMap.moveCamera(center);
					mMap.animateCamera(zoom);
				} catch (GooglePlayServicesNotAvailableException e) {
					Log.e("GeoTager", e.toString());
				}
			}
		}
	}

	private String getNearestCity(Location loc) {
		String city;
		double londonLat = 51.5171, londonLon = 0.1062, nyLat = 40.7142, nyLon = -74.0064, distLondon, distNY;
		distLondon = Math.sqrt(Math.pow(
				(londonLat - loc.getLatitude()), 2)
				+ Math.pow((londonLon - loc.getLongitude()), 2));
		distNY = Math.sqrt(Math.pow((nyLat - loc.getLatitude()), 2)
				+ Math.pow((nyLon - loc.getLongitude()), 2));
		if (distLondon > distNY)
			city = "New York";
		else
			city = "London";
		return city;
	}

	private void displayPOI(String city) {
		ArrayList<String> poiList = new ArrayList<String>();

		if (city.equalsIgnoreCase("london"))
			poiList = poiLondon;
		else
			poiList = poiNY;
		for (final String s : poiList) {
			LinearLayout l = new LinearLayout(getApplicationContext());
			l.setOrientation(LinearLayout.HORIZONTAL);
			ImageView iv = new ImageView(getApplicationContext());
			iv.setBackgroundResource(R.drawable.ic_launcher);
			TextView t = new TextView(getApplicationContext());
			t.setText(s);
			t.setTextSize(20);
			if (s.equalsIgnoreCase("Borough Market")
					|| s.equalsIgnoreCase("Trinity Church")) {
				Toast.makeText(getApplicationContext(), s,
						Toast.LENGTH_LONG).show();
				t.setClickable(true);
				t.setOnClickListener((new View.OnClickListener() {
					public void onClick(View v) {
						Intent intent = new Intent(NearbyActivity.this,
								BoroughMarketActivity.class);
						intent.putExtra(EXTRA_MESSAGE, s);
						startActivity(intent);
					}
				}));
				iv.setClickable(true);
				iv.setOnClickListener((new View.OnClickListener() {
					public void onClick(View v) {
						Intent intent = new Intent(NearbyActivity.this,
								BoroughMarketCompassActivity.class);
						intent.putExtra(EXTRA_MESSAGE, s);
						startActivity(intent);
					}
				}));
			}
			l.addView(t);
			l.addView(iv);
			// linear.addView(l);
		}
	}

	/**
	 * TODO TRAINING
	 */
	private void postOutput() {
		final TextView outputTextView = (TextView) findViewById(R.id.nearby_poi_output_area);
		outputTextView.setText("");

		final Class<?> myclass = getClass();
		for (Annotation ann1 : myclass.getAnnotations()) {
			outputTextView.append(ann1.toString());
		}
	}
}
