package com.geolocateandlearn.model;

public interface Challenge extends Cloneable, Comparable<Challenge> {

	public long getId();

	public String getName();

	public Challenge clone();

}
