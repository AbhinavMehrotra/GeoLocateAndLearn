package com.geolocateandlearn.model;

import java.io.Serializable;

public class ChallengeRecord<T extends Challenge, U extends Number & Serializable> {
	private final T challenge;

	public ChallengeRecord(T challenge1) {
		this.challenge = challenge1;
	}

}
