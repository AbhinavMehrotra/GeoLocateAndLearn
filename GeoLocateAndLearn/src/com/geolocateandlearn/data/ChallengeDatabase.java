package com.geolocateandlearn.data;

import java.util.List;

import com.geolocateandlearn.model.Challenge;

public abstract class ChallengeDatabase {

	public static ChallengeDatabase getInstance() {
		return InMemoryChallengeDatabase.getInstance();
	}

	public abstract List<Challenge> query(ChallengeQuery myQuery);
}
