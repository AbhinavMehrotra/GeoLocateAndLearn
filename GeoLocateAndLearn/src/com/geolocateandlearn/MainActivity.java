package com.geolocateandlearn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

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
		intent=new Intent(this, NearbyActivity.class);
		startActivity(intent);
	}


	//This will be called when me image is clicked
	public void meBtn(View v){
		intent=new Intent(this, MeActivity.class);
		startActivity(intent);
	}


}
