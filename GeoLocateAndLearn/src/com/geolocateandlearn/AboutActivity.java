package com.geolocateandlearn;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.spi.AbstractInterruptibleChannel;
import java.text.StringCharacterIterator;
import java.util.Enumeration;
import java.util.PropertyResourceBundle;
import java.util.Random;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.geolocateandlearn.annotations.ArchitectureSegment;
import com.google.android.gms.internal.cl;

@ArchitectureSegment(segment = "core")
public class AboutActivity extends Activity {

	// private final static String question1 =
	// "Who is buried in the Lincoln Tunnel?";
	// private final static String question2 = "Is Marginal Road important?";
	// private final static String question3 =
	// "Where was the Upside-Down Building?";
	// private final static PracticeChallenge challenge1 = new
	// PracticeChallenge(
	// "The About Challenge", question1, question2, question3);

	// private final static ChallengeRecord<PracticeChallenge> challengeRecord1
	// = new ChallengeRecord<PracticeChallenge>(
	// challenge1);

	// private static final String LOREN_IPSUM_FILENAME = "lorenipsum.txt";

	private final Random random = new Random(System.currentTimeMillis());
	private final int PORT1 = random.nextInt(1024) + 1024;
	private final int LOOPBACK_ADDRESS_INT = 2130706433;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		loadOutput();
	}

	// final TimerTask incrementorTask = new TimerTask() {
	//
	// @Override
	// public void run() {
	// final Handler handler = new Handler(Looper.getMainLooper());
	// handler.post(incrementingRunnable);
	// }
	//
	// };

	// final Runnable incrementingRunnable = new Runnable() {
	//
	// public void run() {
	// final TextView outputTextView = (TextView)
	// findViewById(R.id.about_screen_textView);
	// int value = Integer.parseInt(
	// ((String) outputTextView.getText()).substring(1), 16) + 1;
	// outputTextView
	// .setText(currency_string + Integer.toHexString(value));
	// }
	//
	// };

	private class ClientSocketAsync extends
			AsyncTask<StringBuilder, Void, StringBuilder> {
		final private TextView outputTextView;

		public ClientSocketAsync(TextView outputTextView) {
			this.outputTextView = outputTextView;
		}

		@Override
		protected StringBuilder doInBackground(StringBuilder... outputBuilders) {
			final StringBuilder outputBuilder = outputBuilders[0];
			// Client
			try {
				final Socket clientSocket = new Socket(
						InetAddress.getLocalHost(), PORT1);
				outputBuilder.append("Client socket: ")
						.append(Integer.toString(clientSocket.getLocalPort()))
						.append(" ")
						.append(clientSocket.getLocalSocketAddress())
						.append(" ")
						.append(clientSocket.getReceiveBufferSize())
						.append(" ").append(clientSocket.getSendBufferSize())
						.append(" ").append(clientSocket.getInetAddress())
						.append(" ").append(clientSocket.getLocalAddress())
						.append("").append(clientSocket.getLocalPort())
						.append(clientSocket.getLocalSocketAddress())
						.append(clientSocket.getRemoteSocketAddress());
			} catch (UnknownHostException uhe) {
				throw new RuntimeException(uhe);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

			return outputBuilder;
		}

		@Override
		protected void onPostExecute(StringBuilder outputBuilder) {
			outputTextView.setText(outputBuilder.toString());
		}

	}

	private void loadOutput() {
		final StringBuilder outputBuilder = new StringBuilder();
		final TextView outputTextView = (TextView) findViewById(R.id.about_screen_textView);
		// TODO Put output here.

		// Server
		try {
			final ServerSocket serverSocket = new ServerSocket(PORT1);
			outputBuilder.append("Server socket: ")
					.append(Integer.toString(serverSocket.getLocalPort()))
					.append(" ").append(serverSocket.getLocalSocketAddress())
					.append(" ").append(serverSocket.getReceiveBufferSize())
					.append(" ").append(serverSocket.getSoTimeout())
					.append("\n");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		new ClientSocketAsync(outputTextView).execute(outputBuilder);

		// outputTextView.setText(outputBuilder.toString());
	}
}
