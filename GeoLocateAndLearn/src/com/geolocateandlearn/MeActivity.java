package com.geolocateandlearn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MeActivity extends Activity {

	private Intent intent;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_me);
		ImageView footer_home= (ImageView) findViewById(R.id.footer_home);
		ImageView footer_compass= (ImageView) findViewById(R.id.footer_compass);
		footer_home.setClickable(false);
		footer_compass.setClickable(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_me, menu);
		return true;
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
