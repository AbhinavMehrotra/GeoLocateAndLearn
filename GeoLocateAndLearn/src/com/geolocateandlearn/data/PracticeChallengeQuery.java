package com.geolocateandlearn.data;

public class PracticeChallengeQuery implements ChallengeQuery {
	private boolean requireListening = false;
	private boolean requireSpeaking = false;
	private boolean requireReading = false;
	private boolean requireWriting = false;

	public boolean isPractice() {
		return true;
	}

	public void requireListening(boolean newValue) {
		requireListening = newValue;
	}

	public void requireSpeaking(boolean newValue) {
		requireSpeaking = newValue;
	}

	public void requireReading(boolean newValue) {
		requireReading = newValue;
	}

	public void requireWriting(boolean newValue) {
		requireWriting = newValue;
	}

	public boolean listeningIsRequired() {
		return requireListening;
	}

	public boolean speakingIsRequired() {
		return requireSpeaking;
	}

	public boolean readingIsRequired() {
		return requireReading;
	}

	public boolean writingIsRequired() {
		return requireWriting;
	}

}
