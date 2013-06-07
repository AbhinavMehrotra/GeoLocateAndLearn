package com.geolocateandlearn.model;

import java.io.Serializable;

public class PracticeChallenge implements Challenge, Serializable {
	private static final long serialVersionUID = 1L;
	private final long id;
	private final String name;

	public PracticeChallenge(final String name) {
		this.id = IdGenerator.getInstance().generateNewId();
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public CharSequence getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

}
