package com.geolocateandlearn.model;

public class ChallengeRecord<T extends Challenge> implements Cloneable {
	private final T challenge;

	public ChallengeRecord(T challenge1) {
		this.challenge = challenge1;
	}

	public T getChallenge() {
		return challenge;
	}

	@Override
	public String toString() {
		return "ChallengeRecord [challenge=" + challenge + "]";
	}

	@Override
	public ChallengeRecord<T> clone() {
		final Challenge clonedChallenge = challenge.clone();
		final ChallengeRecord<T> clonedChallengeRecord = new ChallengeRecord(
				clonedChallenge);
		return clonedChallengeRecord;
	}
}
