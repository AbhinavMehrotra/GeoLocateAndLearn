package com.geolocateandlearn.data;

import java.util.ArrayList;
import java.util.List;

import com.geolocateandlearn.model.Challenge;
import com.geolocateandlearn.model.PracticeChallenge;

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

	public List<Challenge> query(ChallengeQuery myQuery) {
		if (myQuery.isPractice()) {
			ArrayList<Challenge> challengeList = new ArrayList<Challenge>();

			// TODO return actual challenges
			final PracticeChallenge dummyChallenge = new PracticeChallenge(
					"dummy challenge");
			challengeList.add(dummyChallenge);

			return challengeList;
		} else {
			throw new IllegalStateException(
					"Not handling real (non-practice) queries yet.");
		}
	}
}
