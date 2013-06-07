package com.geolocateandlearn.data;

/**
 * Singleton.
 * 
 * @author shimon
 * 
 */
public class InMemoryChallengeDatabase extends ChallengeDatabase {
	private static InMemoryChallengeDatabase instance = new InMemoryChallengeDatabase();

	public static InMemoryChallengeDatabase getInstance() {
		return instance;
	}

	/**
	 * Blocked constructor.
	 */
	private InMemoryChallengeDatabase() {

	}
}
