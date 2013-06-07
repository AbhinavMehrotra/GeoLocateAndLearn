package com.geolocateandlearn.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.geolocateandlearn.model.Challenge;
import com.geolocateandlearn.model.CommunicationsSkill;
import com.geolocateandlearn.model.PracticeChallenge;
import static com.geolocateandlearn.model.CommunicationsSkill.*;

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
		createPracticeChallenge("Talk to a stall keeper/seller", LISTENING,
				SPEAKING, READING, WRITING);
		createPracticeChallenge("Update your blog in 150 words or less",
				READING, WRITING);
		createPracticeChallenge("Ask for directions", LISTENING, SPEAKING,
				READING);
		createPracticeChallenge("Buy a train ticket", LISTENING, SPEAKING,
				READING);
		createPracticeChallenge("Reserve a hotel room", LISTENING, SPEAKING,
				WRITING);
		createPracticeChallenge("Book a taxi over the telephone", LISTENING,
				SPEAKING, READING);
		createPracticeChallenge("Order a meal in a restaurant", LISTENING,
				SPEAKING, READING);
		createPracticeChallenge("Buy tickets in a cinema to watch a film",
				LISTENING, SPEAKING, READING);
		createPracticeChallenge("Ask for information at a library", LISTENING,
				SPEAKING, READING);
		createPracticeChallenge("Making small talk with a stranger", LISTENING,
				SPEAKING);
	}

	private void createPracticeChallenge(String challengeName,
			CommunicationsSkill... skills) {
		final PracticeChallenge newChallenge = new PracticeChallenge(
				challengeName);
		for (CommunicationsSkill skill : skills) {
			switch (skill) {
			case LISTENING:
				listeningChallenges.add(newChallenge);
				break;
			case SPEAKING:
				speakingChallenges.add(newChallenge);
				break;
			case READING:
				readingChallenges.add(newChallenge);
				break;
			case WRITING:
				writingChallenges.add(newChallenge);
				break;
			default:
				throw new IllegalArgumentException("Unknown skill: " + skill);
			}
		}
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
