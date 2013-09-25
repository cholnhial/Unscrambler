package com.chol.unscrambler;

import java.io.*;
import java.util.Vector;

public class WordList {
	private FileInputStream fileStream;
	private BufferedReader bufferedReader;
	private DataInputStream dataInputStream;
	private Vector<String> wordListVector;
	private BufferedInputStream bufferedInputStream;

	WordList(String WordListPath) {

		try {
			fileStream = new FileInputStream(WordListPath);
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		dataInputStream = new DataInputStream(fileStream);
		bufferedReader = new BufferedReader(new InputStreamReader(
				dataInputStream));
		wordListVector = new Vector<String>();
	}

	WordList(BufferedInputStream bufferedInputStream) {
		// fileStream = fileInputStream;
		this.bufferedInputStream = bufferedInputStream;

		dataInputStream = new DataInputStream(fileStream);
		bufferedReader = new BufferedReader(new InputStreamReader(
				this.bufferedInputStream));
		wordListVector = new Vector<String>();

	}

	public BufferedReader getBufferedReader() {
		return bufferedReader;
	}

	public Vector<String> getWordList() {
		return wordListVector;
	}

	public void readWordList() {
		String line = new String();
		try {
			while ((line = bufferedReader.readLine()) != null) {
				wordListVector.add(line);
			}
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	public String readWord() {
		try {
			return bufferedReader.readLine();

		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return null;
	}

}
