package com.geolocateandlearn.model;

public class ChallengeRecord<T extends Challenge, U extends Number> {
	private static final String NULL_ARGUMENT_TYPE_NAME = "Void";
	private final T challenge;
	private final String secondArgumentType;

	public <V> ChallengeRecord(T challenge1, V secondArgument) {
		this.challenge = challenge1;
		secondArgumentType = (secondArgument == null) ? NULL_ARGUMENT_TYPE_NAME
				: secondArgument.getClass().getSimpleName();

	}

	public ChallengeRecord(T challenge1) {
		this.challenge = challenge1;
		secondArgumentType = NULL_ARGUMENT_TYPE_NAME;
	}

	public T getChallenge() {
		return challenge;
	}

	public String getSecondArgumentType() {
		return secondArgumentType;
	}

	@Override
	public String toString() {
		return "ChallengeRecord [challenge=" + challenge
				+ ", secondArgumentType=" + secondArgumentType + "]";
	}

}
