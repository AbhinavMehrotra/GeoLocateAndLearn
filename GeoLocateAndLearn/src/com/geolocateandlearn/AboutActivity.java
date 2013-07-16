package com.geolocateandlearn;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import com.geolocateandlearn.annotations.ArchitectureSegment;

@ArchitectureSegment(segment = "core")
public class AboutActivity extends Activity {

	public class Carousel implements Runnable {

		public void run() {
			// TODO Auto-generated method stub

		}

	}

	private static final Random random = new Random(System.currentTimeMillis());

	public class Rider {
		private final int id;

		public Rider(int riderI) {
			id = riderI;
		}

		public void getOnLine() {
			// TODO Auto-generated method stub

			update(id + ": on line\n");
		}

	}

	private static final int MAX_RIDERS = 20;

	private static final int MAX_NEW_RIDER_DELAY = 1000;

	private final Handler uiHandler = new Handler(Looper.getMainLooper());

	private final StringBuffer outputHigherBuffer = new StringBuffer();
	private TextView outputHigherTextView;
	private final StringBuffer outputLowerBuffer = new StringBuffer();
	private TextView outputLowerTextView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		outputHigherTextView = (TextView) findViewById(R.id.about_screen_textView_higher);
		outputLowerTextView = (TextView) findViewById(R.id.about_screen_textView_lower);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		outputLowerBuffer.setLength(0);
		run();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	private class RiderLoader implements Runnable {
		private Carousel carousel;

		public RiderLoader(Carousel carousel) {
			this.carousel = carousel;
		}

		public void run() {
			for (int riderI = 0; riderI < MAX_RIDERS; riderI++) {
				new Rider(riderI).getOnLine();
				try {
					Thread.sleep(random.nextInt(MAX_NEW_RIDER_DELAY));
				} catch (InterruptedException e) {
					update(riderI + ": Time interrupted");
				}
			}
		}
	}

	private void run() {
		Carousel carousel = new Carousel();
		new Thread(carousel).start();
		new Thread(new RiderLoader(carousel)).start();
	}

	private class HigherUpdater implements Runnable {
		private final String message;

		public HigherUpdater(String message) {
			this.message = message;
		}

		public void run() {
			outputHigherBuffer.append(message);
			outputHigherTextView.setText(outputHigherBuffer.toString());
		}
	};

	private class LowerUpdater implements Runnable {
		private final String message;

		public LowerUpdater(String message) {
			this.message = message;
		}

		public void run() {
			outputLowerBuffer.append(message);
			outputLowerTextView.setText(outputLowerBuffer.toString());
		}
	};

	public void update(String message) {
		uiHandler.post(new LowerUpdater(message));
	}
}
