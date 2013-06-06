package com.geolocateandlearn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class LocationsActivity extends Activity {

	private Intent intent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_locations);
		ImageView footer_home= (ImageView) findViewById(R.id.footer_home);
		ImageView footer_compass= (ImageView) findViewById(R.id.footer_compass);
		footer_home.setClickable(true);
		footer_compass.setClickable(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_locations, menu);
		return true;
	}

	//This will be called when London button is clicked
	public void londonBtn(View v){
		intent=new Intent(this, LondonActivity.class);
		startActivity(intent);
	}

	//This will be called when NewYork button is clicked
	public void newyorkBtn(View v){
		intent=new Intent(this, NewYorkActivity.class);
		startActivity(intent);
	}

	//This will be called when Sydney button is clicked
	public void sydneyBtn(View v){
		//do nothing
	}

	//This will be called when footer_home image is clicked
	public void footer_homeBtn(View v){
		intent=new Intent(this, MainActivity.class);
		startActivity(intent);
	}


	//This will be called when footer_compass image is clicked
	public void footer_compassBtn(View v){
		intent=new Intent(this, NearbyActivity.class);
		startActivity(intent);
	}
}
