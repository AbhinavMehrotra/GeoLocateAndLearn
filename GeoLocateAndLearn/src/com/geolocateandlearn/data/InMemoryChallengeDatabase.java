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
		final String[] question = new String[3];
		//
		question[0] = "Ask stall keeper about their stall, e.g. \"When did you set up this stall? Do you have a particular interest in {items}?\"";
		question[1] = "Ask stall keeper about an item that is not listed, e.g. \"I know it is not listed here, but do you sell {item name}? Do you know where I could buy it?\"";
		question[2] = "Ask stall keeper whether they would recommend an item, e.g. \"Is there anything that you would recommend me trying?\"";
		createPracticeChallenge("Talk to a stall keeper/seller", question,
				LISTENING, SPEAKING, READING, WRITING);
		//
		question[0] = "Describe what you saw/who you met";
		question[1] = "Why did you find it interesting?";
		question[2] = "Would you recommend other people to visit it and if yes, why?";
		createPracticeChallenge("Update your blog in 150 words or less",
				question, READING, WRITING);
		//
		question[0] = "Ask for directions, e.g. \"Excuse me, I'm trying to get to {insert POI}. Could you please tell me how to walk there?\"";
		question[1] = "Ask about the amount of time it would take to get there, e.g. \"How long would it take approximately?\"";
		question[2] = "Ask about other means of travelling to your desired location. e.g. \"Could I take public transport instead?\"";
		createPracticeChallenge("Ask for directions", question, LISTENING,
				SPEAKING, READING);
		//
		question[0] = "Buy a ticket, e.g. \"Could I buy a ticket to {city name} please?\"";
		question[1] = "Ask about different ticket prices, e.g. \"How much does that cost for a single and return ticket?\"";
		question[2] = "Find out about the journey, e.g. \"What platform does it leave from? What time is the next train? How long does the journey take?\"";
		createPracticeChallenge("Buy a train ticket", question, LISTENING,
				SPEAKING, READING);
		//
		question[0] = "Ask if a room is available, e.g. \"Could I please book a double room for {date} for {number of days}?\"";
		question[1] = "Ask for the types of rooms and packages that they offer, e.g. \"Are the rooms ensuite? Is breakfast included? Can I use the hotel's facilities?\"";
		question[2] = "Ask for the check-in and check-out times, e.g. \"What time can I check-in from? What is the latest time I can check-out?\"";
		createPracticeChallenge("Reserve a hotel room", question, LISTENING,
				SPEAKING, WRITING);
		//
		question[0] = "Ring up a taxi company to ask about booking a taxi, e.g. \"I'd like to book a taxi from {current location} to {intended destination} please\".";
		question[1] = "Ask for the rates, e.g. \"How much would it cost for you to take me to this destination?\"";
		question[2] = "Ask how long it would take for the taxi driver to reach my current location and how long the journey should take to my intended destination, e.g. \"How long will it take for the taxi to arrive?\"";
		createPracticeChallenge("Book a taxi over the telephone", question,
				LISTENING, SPEAKING, READING);
		//
		question[0] = "Read the menu and place an order, e.g. \"Can I order {item(s)} please?\"";
		question[1] = "Ask what drinks they have on offer, e.g. \"What fizzy drinks do you have?\"";
		question[2] = "Ask what specials they have on offer e.g. \"What specials do you have on offer today?\"";
		createPracticeChallenge("Order a meal in a restaurant", question,
				LISTENING, SPEAKING, READING);
		//
		// instructions =
		// "Decide who will be the ticket-seller at the cinema and who will be the cinema-goer. The ticket-seller should write down a list of 5 films and times they are showing. Swap roles and practice this a few times.";
		question[0] = "Buy cinema tickets for the film you want to watch, e.g. \"I'd like {number of tickets} for {name of film} please.\"";
		question[1] = "Ask how much they cost and whether there are concessions for students, e.g. \"How much is one ticket? Do students receive a discount?\"";
		question[2] = "Buy some snacks, e.g. \"Where can I buy some popcorn?\"";
		createPracticeChallenge("Buy tickets in a cinema to watch a film",
				question, LISTENING, SPEAKING, READING);
		//
		// instructions =
		// "Decide who will be the librarian or who will be the customer. The librarian should write down a list of 10 books they have in stock. Swap roles and practice this a few times.";
		question[0] = "Enquire about a specific book, e.g. \"Do you have {book} in stock?\"";
		question[1] = "Ask what book the librarian would recommend, e.g. \"Which book would you recommend?\"";
		question[2] = "Ask what genre the libarian would recommend,e.g. \"Which genre would you recommend?\"";
		createPracticeChallenge("Ask for information at a library", question,
				LISTENING, SPEAKING, READING);
		//
		// instructions =
		// "Decide who will be person A and who will be person B. Person A should attempt to initiate a conversation with person B. Swap roles and practice this a few times.";
		question[0] = "Ask how person B is doing, e.g. \"How are you today?\"";
		question[1] = "Make general comments about the environment around you, e..g the weather, traffic conditions, etc., e.g. \"It is very sunny today! Pity the traffic is so bad.\"";
		question[2] = "Ask about person B's day, e.g. \"How is your day so far? Are you doing anything interesting today?\"";
		createPracticeChallenge("Making small talk with a stranger", question,
				LISTENING, SPEAKING);
	}

	private void createPracticeChallenge(String challengeName,
			String[] question, CommunicationsSkill... skills) {
		final PracticeChallenge newChallenge = new PracticeChallenge(
				challengeName, question[0], question[1], question[2]);
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
