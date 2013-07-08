package com.geolocateandlearn.model;

import java.io.Serializable;

public class PracticeChallenge implements Challenge, Serializable {
	private static final long serialVersionUID = 1L;
	private final long id;
	private final String name;
	private final String[] question = new String[3];

	public PracticeChallenge(final String name, final String question1,
			final String question2, final String question3) {
		this.question[0] = question1;
		this.question[1] = question2;
		this.question[2] = question3;
		this.id = IdGenerator.getInstance().generateNewId();
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public CharSequence getName() {
		return name;
	}

	@Override
	public String toString() {
		final StringBuilder output = new StringBuilder();
		output.append(name);
		output.append(" -- ");
		int questionsSpecified = 0;
		for (int qi = 0; qi < 3; qi++) {
			if (question[qi] != null && question[qi].length() != 0)
				questionsSpecified++;
		}
		output.append(questionsSpecified);
		output.append(" questions are specified");
		return output.toString();
	}

	public CharSequence getQuestion(int questionNumber) {
		return question[questionNumber - 1];
	}
}
