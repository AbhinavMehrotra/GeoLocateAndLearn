package com.geolocateandlearn;

import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private Intent intent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ImageView footer_home= (ImageView) findViewById(R.id.footer_home);
		ImageView footer_compass= (ImageView) findViewById(R.id.footer_compass);
		footer_home.setClickable(false);
		footer_compass.setClickable(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	//This will be called when about image is clicked
	public void aboutBtn(View v){
		intent=new Intent(this, AboutActivity.class);
		startActivity(intent);
	}


	//This will be called when challenge image is clicked
	public void challengeBtn(View v){
		intent=new Intent(this, ChallengeActivity.class);
		startActivity(intent);
	}


	//This will be called when nearby image is clicked
	public void nearbyBtn(View v){
//		intent=new Intent(this, NearbyActivity.class);
//		startActivity(intent);
		new GeoTrack(getApplicationContext()).execute();
	}


	//This will be called when me image is clicked
	public void meBtn(View v){
		intent=new Intent(this, MeActivity.class);
		startActivity(intent);
	}

	//This will be called when footer_home image is clicked
	public void footer_homeBtn(View v){
		//do nothing as we are on the same page
	}


	//This will be called when footer_compass image is clicked
	public void footer_compassBtn(View v){
		intent=new Intent(this, NearbyActivity.class);
		startActivity(intent);
	}
	
	private class GeoTrack extends AsyncTask<Location,Location,Void>{

		LocationManager lm;
		LocationListener ll;
		Context context;
		String NP, TAG="GEOTRACK";
		Location newlocation=null;
		
		public GeoTrack(Context context){
			this.context=context;
		}
		
		protected Location doInBackground(Void... arg0) {
			sensorGPS();
			getGeo();
			return newlocation;
		}

		

		private void getGeo(){
			if(lm.isProviderEnabled(LocationManager.GPS_PROVIDER)){
				Log.d(TAG,"Provider: GPS");
				lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, ll);
			}
			else if(isRecent()){ //last known loc is from last 10min
				Log.d(TAG,"Last known location taken, Provider: GPS");
				Location loc=lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			}
			else{		
				Log.d(TAG,"Provider: Network");
				lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, ll);
			}
		    String provider = lm.getBestProvider(new Criteria(), true);
		    lm.requestLocationUpdates(provider, 10000, 0, ll);

			Log.d(TAG,"Location requested");
		}
		
		private Boolean isRecent(){
			
			long c=0;
			try{
				c=lm.getLastKnownLocation(LocationManager.GPS_PROVIDER).getTime();
			}
			catch(Exception e){
				e.toString();
			}
			long present;
			Calendar cl=Calendar.getInstance();
			cl.set(1970, 01, 01);
			present=cl.getTimeInMillis();
			if(present-c > (30*60*1000)){
				return false;
			}
			else{
				return true;
			}
		}

		private void sensorGPS(){
			lm=(LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
			ll = new LocationListener() {			
				public void onLocationChanged(Location location) {
					Log.d(TAG,"Location Detected:"+location);
					newlocation=location;
					lm.removeUpdates(ll);
				}
				public void onStatusChanged(String provider, int status, Bundle extras) {
					Log.d(TAG,"LocationListener onStatusChange:"+provider+";"+status+";"+extras);
					lm.removeUpdates(ll);
				}
				public void onProviderEnabled(String provider) {
					Log.d(TAG,"LocationListener onProviderEnabled");
					lm.removeUpdates(ll);
				}
				public void onProviderDisabled(String provider) {	
					Log.d(TAG,"LocationListener onProviderDisabled:"+provider);					
					lm.removeUpdates(ll);
				}
			};
		}

		protected Void doInBackground(Location... params) {
			// TODO Auto-generated method stub
			return null;
		}
		
		
		
	}

}
