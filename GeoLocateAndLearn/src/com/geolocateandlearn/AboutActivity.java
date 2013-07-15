package com.geolocateandlearn;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import com.geolocateandlearn.annotations.ArchitectureSegment;

@ArchitectureSegment(segment = "core")
public class AboutActivity extends Activity implements FlukeListener {

	public class FlukeUpdate {

		private final Date timestamp;

		public FlukeUpdate(Date instamp) {
			timestamp = instamp;
		}

		public Date getStamp() {
			return timestamp;
		}

	}

	public class Fluke {
		final Set<FlukeListener> listeners = new HashSet<FlukeListener>();

		public boolean addFlukeListener(FlukeListener newListener) {
			return listeners.add(newListener);
		}

		public boolean removeFlukeListener(FlukeListener oldListener) {
			return listeners.remove(oldListener);
		}

		public void modify(long l) {
			final FlukeUpdate newUpdate = new FlukeUpdate(new Date());
			for (FlukeListener listener : listeners) {
				listener.update(newUpdate);
			}
		}

	}

	private Fluke fluke = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		fluke = new Fluke();
		loadOutput();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onStart() {
		super.onStart();
		fluke.addFlukeListener(this);
		fluke.modify(37L);
	}

	@Override
	protected void onStop() {
		fluke.removeFlukeListener(this);
		super.onStop();
	}

	private void loadOutput() {
		// final TextView outputTextView = (TextView)
		// findViewById(R.id.about_screen_textView_lower);
		// final StringBuffer output = new StringBuffer();
		// TODO Put output here.


		// outputTextView.setText(output.toString());
	}

	private Handler mHandler = new Handler(Looper.getMainLooper());

	private class Updater implements Runnable {
		private final FlukeUpdate updateData;

		public Updater(FlukeUpdate flukeUpdate) {
			updateData = flukeUpdate;
		}

		public void run() {
			final TextView outputTextView = (TextView) findViewById(R.id.about_screen_textView_lower);
			outputTextView.setText("Updated at "
					+ updateData.getStamp());
		}
	};

	public void update(FlukeUpdate flukeUpdate) {
		mHandler.post(new Updater(flukeUpdate));
	}
}
