package com.geolocateandlearn.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	private final Set<Challenge> listeningChallenges = new HashSet<Challenge>();
	private final Set<Challenge> speakingChallenges = new HashSet<Challenge>();
	private final Set<Challenge> readingChallenges = new HashSet<Challenge>();
	private final Set<Challenge> writingChallenges = new HashSet<Challenge>();

	/**
	 * Blocked constructor.
	 */
	private InMemoryChallengeDatabase() {
		createTalkToAStallSeller();
		createUpdateYourBlog();
	}

	private void createTalkToAStallSeller() {
		final PracticeChallenge newChallenge = new PracticeChallenge(
				"Talk to a stall keeper/seller");
		listeningChallenges.add(newChallenge);
		speakingChallenges.add(newChallenge);
		readingChallenges.add(newChallenge);
		writingChallenges.add(newChallenge);
	}

	private void createUpdateYourBlog() {
		final PracticeChallenge newChallenge = new PracticeChallenge(
				"Update your blog in 150 words or less");
		// listeningChallenges.add(newChallenge);
		// speakingChallenges.add(newChallenge);
		readingChallenges.add(newChallenge);
		writingChallenges.add(newChallenge);
	}

	public List<Challenge> query(ChallengeQuery myQuery) {
		if (myQuery.isPractice()) {
			ArrayList<Challenge> challengeList = new ArrayList<Challenge>();
			final PracticeChallengeQuery practiceQuery = (PracticeChallengeQuery) myQuery;
			boolean firstSetLoaded = false;

			if (practiceQuery.readingIsRequired()) {
				challengeList.addAll(readingChallenges);
				firstSetLoaded = true;
			}
			if (practiceQuery.writingIsRequired()) {
				if (firstSetLoaded) {
					challengeList.retainAll(writingChallenges);
				} else {
					challengeList.addAll(writingChallenges);
					firstSetLoaded = true;
				}
			}
			if (practiceQuery.listeningIsRequired()) {
				if (firstSetLoaded) {
					challengeList.retainAll(listeningChallenges);
				} else {
					challengeList.addAll(listeningChallenges);
					firstSetLoaded = true;
				}
			}
			if (practiceQuery.speakingIsRequired()) {
				if (firstSetLoaded) {
					challengeList.retainAll(speakingChallenges);
				} else {
					challengeList.addAll(speakingChallenges);
					firstSetLoaded = true;
				}
			}
			return challengeList;
		} else {
			throw new IllegalStateException(
					"Not handling real (non-practice) queries yet.");
		}
	}
}
