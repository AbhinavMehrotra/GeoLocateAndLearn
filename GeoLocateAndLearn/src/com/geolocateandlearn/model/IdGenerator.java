package com.geolocateandlearn.model;

/**
 * Singleton
 * 
 * @author shimon
 * 
 */
public class IdGenerator {
	private static final IdGenerator instance = new IdGenerator();

	public static IdGenerator getInstance() {
		return instance;
	}

	private long nextId = 1L;

	private IdGenerator() {

	}

	public synchronized long generateNewId() {
		return nextId++;
	}
}
