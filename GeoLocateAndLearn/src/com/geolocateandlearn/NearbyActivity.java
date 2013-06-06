package com.geolocateandlearn;

import java.util.List;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class NearbyActivity extends FragmentActivity {

	private GoogleMap mMap;
	private MapView mapView;
	private MapController mapController;
	private LocationManager locationManager;
	private GeoPoint p;
	List<Overlay> listOfOverlays;
	private String Tag = "Geo Tagger";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nearby);
		setUpMapIfNeeded();
		// initMap();
		// LocationManager lm = (LocationManager)
		// getSystemService(LOCATION_SERVICE);
		// String provider = lm.getBestProvider(new Criteria(), true);
		// lm.requestLocationUpdates(provider, 10000, 0, this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		setUpMapIfNeeded();
	}

	private void setUpMapIfNeeded() {
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
					Location l = mMap.getMyLocation();
					LatLng current = new LatLng(l.getLatitude(),
							l.getLongitude());
					CameraUpdate center = CameraUpdateFactory
							.newLatLng(current);
					CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
					mMap.addMarker(new MarkerOptions()
							.icon(BitmapDescriptorFactory
									.defaultMarker(BitmapDescriptorFactory.HUE_RED))
							.position(current));
					mMap.moveCamera(center);
					mMap.animateCamera(zoom);

					// setUpMap();
				} catch (GooglePlayServicesNotAvailableException e) {
					Log.e("GeoTager", e.toString());
				}
			}
		}
	}
}
