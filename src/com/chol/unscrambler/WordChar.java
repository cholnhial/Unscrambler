package com.chol.unscrambler;

import java.lang.Character;

class WordChar {
	private char character;
	private int characterCount;

	WordChar() {

	}

	WordChar(char c) {
		character = c;
	}

	public char getChar() {
		return character;
	}

	public int getCount() {
		return characterCount;
	}

	public void setChar(char c) {
		character = c;
	}

	public void setCount(int count) {
		characterCount = count;
	}
}