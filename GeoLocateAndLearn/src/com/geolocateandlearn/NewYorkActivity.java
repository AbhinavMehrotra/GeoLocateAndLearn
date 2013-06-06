package com.geolocateandlearn;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class NewYorkActivity extends Activity {

	private Intent intent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_york);
		ImageView footer_home= (ImageView) findViewById(R.id.footer_home);
		ImageView footer_compass= (ImageView) findViewById(R.id.footer_compass);
		footer_home.setClickable(true);
		footer_compass.setClickable(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_new_york, menu);
		return true;
	}


	//This will be called when castle image is clicked
	public void castleBtn(View v){
		intent=new Intent(this, CastleActivity.class);
		startActivity(intent);
	}


	//This will be called when castle_compass image is clicked
	public void castleCompassBtn(View v){
		intent=new Intent(this, CastleCompassActivity.class);
		startActivity(intent);
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
