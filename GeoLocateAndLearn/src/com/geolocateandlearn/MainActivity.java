package com.geolocateandlearn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
		intent=new Intent(this, LocationsActivity.class);
		startActivity(intent);
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

}
