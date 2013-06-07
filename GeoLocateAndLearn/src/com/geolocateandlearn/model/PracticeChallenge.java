package com.geolocateandlearn.model;

public class PracticeChallenge implements Challenge {
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
		// TODO fix dummy value
		return name + Long.toString(id);
	}

}
