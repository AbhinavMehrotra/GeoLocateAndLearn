package com.geolocateandlearn.model;

public class ChallengeResponse {
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
		// TODO Auto-generated method stub
		answer[answerNumber - 1] = answerText;
	}
}