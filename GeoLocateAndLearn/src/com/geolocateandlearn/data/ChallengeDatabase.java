package com.geolocateandlearn.data;

import java.util.ArrayList;
import java.util.List;

import com.geolocateandlearn.model.Challenge;

public abstract class ChallengeDatabase {

	public static ChallengeDatabase getInstance() {
		return InMemoryChallengeDatabase.getInstance();
	}

	public List<Challenge> query(ChallengeQuery myQuery) {
		if (myQuery.isPractice()) {
			// TODO Auto-generated method stub
			return new ArrayList<Challenge>();
		} else {
			throw new IllegalStateException(
					"Not handling real (non-practice) queries yet.");
		}
	}

}
