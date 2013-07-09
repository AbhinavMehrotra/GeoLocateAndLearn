package com.geolocateandlearn.model;

public class ChallengeRecord<T extends Challenge> {
	private final T challenge;

	public <V> ChallengeRecord(T challenge1, V secondArgument) {
		this.challenge = challenge1;
	}

	public ChallengeRecord(T challenge1) {
		this.challenge = challenge1;
	}

	public T getChallenge() {
		return challenge;
	}

	@Override
	public String toString() {
		return "ChallengeRecord [challenge=" + challenge
				+ ", Infinities=" + Double.POSITIVE_INFINITY + "/"
				+ Float.NEGATIVE_INFINITY + "]";
	}

}
