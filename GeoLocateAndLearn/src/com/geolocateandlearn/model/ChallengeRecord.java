package com.geolocateandlearn.model;

public class ChallengeRecord<T extends Challenge, U extends Number> {
	private final T challenge;

	public <V> ChallengeRecord(T challenge1) {
		this.challenge = challenge1;
	}

}
