package com.geolocateandlearn.model;

public class PointOfInterest {

	private final String name;

	public String getName() {
		return name;
	}

	public PointOfInterest(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "POI: " + name;
	}

}
