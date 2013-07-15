package com.geolocateandlearn;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Random;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.geolocateandlearn.annotations.ArchitectureSegment;

@ArchitectureSegment(segment = "core")
public class AboutActivity extends Activity {

	private final Random random = new Random(System.currentTimeMillis());
	private final int PORT1 = random.nextInt(1024) + 1024;
	private boolean runThreads = true;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		loadOutput();
	}

	@Override
	protected void onDestroy() {
		runThreads = false;
		super.onDestroy();
	}

	private class ClientSocketAsync extends
			AsyncTask<StringBuffer, StringBuffer, StringBuffer> {
		final private TextView outputTextView;

		public ClientSocketAsync(TextView outputTextView) {
			this.outputTextView = outputTextView;
		}

		@Override
		protected StringBuffer doInBackground(StringBuffer... outputBuilders) {
			Thread.currentThread().setName("About Client");

			final StringBuffer outputBuilder = outputBuilders[0];

			outputBuilder.append("CLIENT: start execution\n");
			publishProgress(outputBuilder);

			Socket clientSocket = null;
			try {
				final byte[] localIpAddress = { 10, 0, 2, 2 };
				InetAddress inetAddressToRequest = InetAddress
						.getByAddress(localIpAddress);
				outputBuilder.append("CLIENT: about to request on socket "
						+ inetAddressToRequest + "\n");
				publishProgress(outputBuilder);

				while (runThreads) {
					clientSocket = new Socket();
					final InetSocketAddress remoteSocketAddress = new InetSocketAddress(
							inetAddressToRequest, PORT1);
					try {
					clientSocket.connect(remoteSocketAddress, 500);
					} catch (SocketTimeoutException ste) {
						outputBuilder.append(" <TO>");
						publishProgress(outputBuilder);
					}
					if (clientSocket.isConnected()) {
						outputBuilder
								.append((clientSocket.isConnected()) ? "CLIENT: CONNECTED\n"
										: "CLIENT: NOT_CONN\n");
						publishProgress(outputBuilder);

						final InputStream clientis = clientSocket
								.getInputStream();
						final ObjectInputStream clientois = new ObjectInputStream(
								clientis);

						outputBuilder.append("CLIENT: point 2\n");
						publishProgress(outputBuilder);

						final OutputStream clientos = clientSocket
								.getOutputStream();
						final ObjectOutputStream clientoos = new ObjectOutputStream(
								clientos);

						outputBuilder.append("CLIENT: point 3\n");
						publishProgress(outputBuilder);

						final Date currentTime = new Date();
						outputBuilder
								.append("CLIENT: Request sum of time digits at ")
								.append(currentTime).append(".\n");
						publishProgress(outputBuilder);
						clientoos.writeObject(currentTime);

						outputBuilder.append("CLIENT: point 4\n");
						publishProgress(outputBuilder);
					} else {
						outputBuilder.append(" <NC>");
						publishProgress(outputBuilder);
					}
					Thread.yield();
				}
			} catch (UnknownHostException uhe) {
				throw new RuntimeException(uhe);
			} catch (IOException e) {
				throw new RuntimeException(e);
			} finally {
				if (clientSocket != null)
					try {
						clientSocket.close();
					} catch (IOException e) {
						outputBuilder.append("Closing client socket: "
								+ e.getLocalizedMessage());
					}
			}
			return outputBuilder;
		}

		@Override
		protected void onProgressUpdate(StringBuffer... outputBuilder) {
			outputTextView.setText(outputBuilder[0].toString());
		}

		@Override
		protected void onPostExecute(StringBuffer outputBuilder) {
			outputTextView.setText(outputBuilder.toString());
		}

	}

	private class ServerSocketAsync extends
			AsyncTask<StringBuffer, StringBuffer, StringBuffer> {
		final private TextView outputTextView;

		public ServerSocketAsync(TextView outputTextView) {
			this.outputTextView = outputTextView;
		}

		@Override
		protected StringBuffer doInBackground(StringBuffer... outputBuilders) {
			Thread.currentThread().setName("About Client");

			final StringBuffer outputBuilder = outputBuilders[0];

			ServerSocket serverSocket = null;
			Socket activeServerSocket = null;
			try {
				serverSocket = new ServerSocket(PORT1);
				outputBuilder.append("SERVER: listening on socket "
						+ serverSocket.getInetAddress() + "\n");
				publishProgress(outputBuilder);

				serverSocket.setSoTimeout(200);

				while (runThreads) {
					try {
						activeServerSocket = serverSocket.accept();
						outputBuilder.append("SERVER: accepted connection\n");
						publishProgress(outputBuilder);

						final InputStream serveris = activeServerSocket
								.getInputStream();
						final ObjectInputStream serverois = new ObjectInputStream(
								serveris);

						outputBuilder.append("SERVER: point 3\n");
						publishProgress(outputBuilder);

						final OutputStream serveros = activeServerSocket
								.getOutputStream();
						final ObjectOutputStream serveroos = new ObjectOutputStream(
								serveros);

						outputBuilder.append("SERVER: point 4\n");
						publishProgress(outputBuilder);

						final Date dateReceivedByServer = (Date) serverois
								.readObject();
						outputBuilder
								.append("SERVER: Current time received is ")
								.append(dateReceivedByServer).append(".\n");
					} catch (SocketTimeoutException ste) {
						outputBuilder.append("*-");
						publishProgress(outputBuilder);
					}
					Thread.yield();
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
			return outputBuilder;
		}

		@Override
		protected void onProgressUpdate(StringBuffer... outputBuilder) {
			outputTextView.setText(outputBuilder[0].toString());
		}

		protected void onPostExecute(StringBuffer outputBuilder) {
			outputTextView.setText(outputBuilder.toString());
		}
	}

	private void loadOutput() {
		final StringBuffer outputBuilderServer = new StringBuffer();
		final TextView outputTextViewServer = (TextView) findViewById(R.id.about_screen_textView);
		final StringBuffer outputBuilderClient = new StringBuffer();
		final TextView outputTextViewClient = (TextView) findViewById(R.id.about_screen_textView_lower);
		// TODO Put output here.

		new ServerSocketAsync(outputTextViewServer)
				.execute(outputBuilderServer);

		new ClientSocketAsync(outputTextViewClient)
				.execute(outputBuilderClient);

		// outputTextView.setText(outputBuilder.toString());
	}
}
