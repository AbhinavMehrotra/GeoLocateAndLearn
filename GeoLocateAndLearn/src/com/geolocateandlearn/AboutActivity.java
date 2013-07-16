package com.geolocateandlearn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Semaphore;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import com.geolocateandlearn.annotations.ArchitectureSegment;

@ArchitectureSegment(segment = "core")
public class AboutActivity extends Activity {

	public class Carousel implements Runnable {
		private static final int MAX_CONCURRENT_RIDERS = 6;

		private static final long RIDE_TIME = 300;

		private final List<Rider> lineToRide = new ArrayList<Rider>();
		private final Set<Rider> onHorses = new HashSet<Rider>();

		private final Semaphore ridePermits = new Semaphore(
				MAX_CONCURRENT_RIDERS, true);

		public void run() {
			// TODO Auto-generated method stub
			while (true) {
				while (ridePermits.availablePermits() != 0) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// no-op
					}
				}
				updateHigher(" Start");
				try {
					Thread.sleep(RIDE_TIME);
				} catch (InterruptedException e) {
					updateHigher("Ride_interrupted");
				}
				updateHigher(" Stop");
				for (Rider rider : new ArrayList<Rider>(onHorses)) {
					onHorses.remove(rider);
					ridePermits.release();
					rider.depart();
					updateHigher(" " + rider.getId() + "_Exit");
				}
			}
		}

		public void getOnLine(Rider rider) {
			// TODO Auto-generated method stub
			updateHigher(" " + rider.getId() + "_Line");
			lineToRide.add(rider);
			ridePermits.acquireUninterruptibly();
			lineToRide.remove(rider);
			onHorses.add(rider);
			updateHigher(" " + rider.getId() + "_Horse");
		}

	}

	private static final Random random = new Random(System.currentTimeMillis());

	public class Rider implements Runnable {
		private final int id;
		private final Carousel carousel;

		public Rider(int riderI, Carousel carousel) {
			id = riderI;
			this.carousel = carousel;
		}

		public void depart() {
			updateLower(" " + id + "_leave");
		}

		public int getId() {
			return id;
		}

		public void getOnLine() {
			carousel.getOnLine(this);
		}

		public void run() {
			updateLower(" " + id + "_arrive");
			getOnLine();
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
				new Thread(new Rider(riderI, carousel)).start();
				try {
					Thread.sleep(random.nextInt(MAX_NEW_RIDER_DELAY));
				} catch (InterruptedException e) {
					updateLower(riderI + ": Time interrupted");
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

	public void updateLower(String message) {
		uiHandler.post(new LowerUpdater(message));
	}

	public void updateHigher(String message) {
		uiHandler.post(new HigherUpdater(message));
	}
}
