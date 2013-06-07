package com.geolocateandlearn.model;

import java.io.Serializable;

public class ChallengeResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	final private Challenge originalChallenge;
	final private String[] answer = new String[3];

	public ChallengeResponse(PracticeChallenge theChallenge) {
		originalChallenge = theChallenge;
	}

	/**
	 * 
	 * @param answerNumber
	 *            int 1..N
	 * @param answerText
	 *            String
	 */
	public void setAnswer(int answerNumber, String answerText) {
		answer[answerNumber - 1] = answerText;
	}

	public Challenge getChallenge() {
		return originalChallenge;
	}

	public String getAnswer(int which) {
		return answer[which - 1];
	}
}