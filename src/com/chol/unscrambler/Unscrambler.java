package com.chol.unscrambler;

import java.util.Vector;

public class Unscrambler {
	private String wordString;
	private String charString;

	Unscrambler(String wordString, String charString) {
		this.wordString = new String();
		this.charString = new String();

		this.wordString = wordString;
		this.charString = charString;
	}

	public boolean containsCharacters() {
		if (wordString == null && charString == null)
			return false;

		if (wordString.length() == 0 && charString.length() == 0)
			return false;
		if (wordString.length() != charString.length()) {
			return false;
		}

		Vector<WordChar> wordWordChars = new Vector<WordChar>();
		boolean existAlreadyInVector = false;

		/* For wordString */
		for (int i = 0; i < wordString.length(); i++) {
			WordChar tempWordChar = new WordChar();
			tempWordChar.setChar(wordString.charAt(i));
			tempWordChar.setCount(1);

			for (int j = 0; j < wordWordChars.size(); j++) {
				if (wordWordChars.get(j).getChar() == wordString.charAt(i)) {
					wordWordChars.get(j).setCount(
							wordWordChars.get(j).getCount() + 1);
					existAlreadyInVector = true;
				}
			}

			if (!existAlreadyInVector) {
				wordWordChars.add(tempWordChar);
			}

			existAlreadyInVector = false;
		}

		/* For charString */
		Vector<WordChar> wordWordChars1 = new Vector<WordChar>();
		for (int i = 0; i < charString.length(); i++) {
			WordChar tempWordChar = new WordChar();
			tempWordChar.setChar(charString.charAt(i));
			tempWordChar.setCount(1);

			for (int j = 0; j < wordWordChars1.size(); j++) {
				if (wordWordChars1.get(j).getChar() == charString.charAt(i)) {
					wordWordChars1.get(j).setCount(
							wordWordChars1.get(j).getCount() + 1);
					existAlreadyInVector = true;
				}
			}

			if (!existAlreadyInVector) {
				wordWordChars1.add(tempWordChar);
			}

			existAlreadyInVector = false;
		}

		/* Now compare the two to find out if they truely match */
		int matchCount = 0;
		if (wordWordChars.size() == wordWordChars1.size()) {
			for (int i = 0; i < wordWordChars.size(); i++) {
				for (int j = 0; j < wordWordChars1.size(); j++) {
					if (wordWordChars.get(i).getChar() == wordWordChars1.get(j)
							.getChar()
							&& wordWordChars.get(i).getCount() == wordWordChars1
									.get(j).getCount()) {
						matchCount += wordWordChars.get(i).getCount();
					}

				}
			}

		}

		if (matchCount == wordString.length())
			return true;

		return false;
	}

	public void setWord(String wordString, String charString) {
		this.wordString = wordString;
		this.charString = charString;
	}

	public static int getASCIITotal(String word) {
		if (word == null)
			return 0;

		int total = 0;
		for (char ch : word.toCharArray()) {
			total += ch;

		}

		return total;
	}

	public static void main(String argurments[]) {
		Unscrambler unscramble = new Unscrambler("", "");

		WordList wordList = new WordList("");
		wordList.readWordList();

		String pattern = "extent";
		Vector<String> words = new Vector<String>();
		words = wordList.getWordList();

		for (int i = 0; i < words.size(); i++) {

			if (words.get(i).length() == pattern.length()) {
				unscramble.setWord(words.get(i).toLowerCase(),
						pattern.toLowerCase());
				if (unscramble.containsCharacters()) {
					System.out.println("Match Found: " + words.get(i));
				}
			}
		}

	}

}