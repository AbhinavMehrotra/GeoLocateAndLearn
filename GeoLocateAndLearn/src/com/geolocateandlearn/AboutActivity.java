package com.geolocateandlearn;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import com.geolocateandlearn.annotations.ArchitectureSegment;

@SuppressLint("SimpleDateFormat")
@ArchitectureSegment(segment = "core")
public class AboutActivity extends Activity {

	private static final long RIDE_TIME = 300;

	private static final SimpleDateFormat MMSS_TIME_FORMAT = new SimpleDateFormat(
			"mm:ss");

	public class Carousel implements Runnable {
		private class BrassRingDispenser implements Runnable {
			// Every fourth rider gets a brass ring.
			private CountDownLatch brassRingCounter = null;

			public void run() {
				while (true) {
					brassRingCounter = new CountDownLatch(4);
					try {
						brassRingCounter.await();
						updateHigher(" BRASS");
					} catch (InterruptedException e) {
						updateHigher(" SKIP_BRASS");
					}

				}
			}

			public void grab(Rider rider) {
				final Date eventTime = new Date();
				brassRingCounter.countDown();
				rider.setRingGrabTime(eventTime);
			}
		}

		private static final int MAX_CONCURRENT_RIDERS = 6;

		private final Set<Rider> onHorses = new HashSet<Rider>();

		private final Semaphore ridePermits = new Semaphore(
				MAX_CONCURRENT_RIDERS, true);

		private final BrassRingDispenser ringDispenser = new BrassRingDispenser();

		private final Runnable gateFlag = new Runnable() {

			public void run() {
				updateHigher(" GATE");
			}
		};

		private final CyclicBarrier entranceGate = new CyclicBarrier(
				MAX_CONCURRENT_RIDERS, gateFlag);

		public void run() {
			// TODO Rewrite code -- now utilizing entranceGate

			executor.execute(ringDispenser);
			while (true) {
				for (Rider rider : new ArrayList<Rider>(onHorses)) {
					rider.depart();
				}
			}
		}

		public void debark(Rider rider) {
			onHorses.remove(rider);
		}

		public void board(Rider rider) {
			ringDispenser.grab(rider);
			onHorses.add(rider);
		}

		public void approachGate(Rider rider) {
			try {
				entranceGate.await();
				updateHigher(" " + rider.getId() + "_PASS");
			} catch (InterruptedException e) {
				updateHigher(" GATE_INTERRUPTED");
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				updateHigher(" GATE_BROKEN");
			}
		}

	}

	private static final Random random = new Random(
			System.currentTimeMillis());

	private class Rider implements Runnable {
		private final int id;
		private final Carousel carousel;
		private Date ringGrabTime;

		public Rider(int riderI, Carousel carousel) {
			id = riderI;
			this.carousel = carousel;
		}

		public void setRingGrabTime(Date newRingGrabTime) {
			// TODO Auto-generated method stub
			this.ringGrabTime = newRingGrabTime;
		}

		public int getId() {
			return id;
		}

		public void run() {
			updateLower(" " + id + "_arrive");
			arrive();
			updateLower(" " + id + "_ride");
			Thread.sleep(RIDE_TIME);
			// TODO HERE
			exitGate.await();
			depart();
	
		}

		private void arrive() {
			carousel.approachGate(this);
			carousel.board(this);
				}

		public void depart() {
			carousel.debark(this);
			updateLower(" " + id + "_leave");
			if (ringGrabTime == null) {
				updateHigher(" " + id + "_NO_RING");
			} else {
				updateHigher(" " + id + "_ring_at_"
						+ MMSS_TIME_FORMAT.format(ringGrabTime));
			}
		}

	}

	private class RiderLoader implements Runnable {
		private Carousel carousel;

		public RiderLoader(Carousel carousel) {
			this.carousel = carousel;
		}

		public void run() {
			int cumulativeDelayMsec = 0;
			for (int riderI = 0; riderI < MAX_RIDERS; riderI++) {
				cumulativeDelayMsec += random
						.nextInt(MAX_NEW_RIDER_DELAY);
				executor.schedule(new Rider(riderI, carousel),
						cumulativeDelayMsec, TimeUnit.MILLISECONDS);
			}
		}
	}

	private static final int MAX_RIDERS = 21;

	private static final int MAX_NEW_RIDER_DELAY = 1000;

	private final Handler uiHandler = new Handler(
			Looper.getMainLooper());

	private final StringBuffer outputHigherBuffer = new StringBuffer();
	private TextView outputHigherTextView;
	private final StringBuffer outputLowerBuffer = new StringBuffer();
	private TextView outputLowerTextView;

	private ScheduledThreadPoolExecutor executor;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		outputHigherTextView = (TextView) findViewById(R.id.about_screen_textView_higher);
		outputLowerTextView = (TextView) findViewById(R.id.about_screen_textView_lower);
		executor = new ScheduledThreadPoolExecutor(10);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		outputHigherBuffer.setLength(0);
		outputLowerBuffer.setLength(0);
		run();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	private void run() {
		final Carousel carousel = new Carousel();
		executor.execute(carousel);
		executor.execute(new RiderLoader(carousel));
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
