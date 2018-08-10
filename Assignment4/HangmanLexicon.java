/*
 * File: HangmanLexicon.java
 * -------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import acm.util.*;
import java.util.*;
import java.io.*;

public class HangmanLexicon {
			
	// This is the HangmanLexicon constructor
	public HangmanLexicon() {
		wordList = new ArrayList<String>();	
		try {
			BufferedReader rd = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			while (true) {
				String line = rd.readLine();
				wordList.add(line);
				if (line == null) {
					break;
				}
			}
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
	}
	
	/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return wordList.size();
	}
	
	/** Returns the word at the specified index. */
	public String getWord(int index) {
		return wordList.get(index);
	}
	
	/* Private instance variables */
	private ArrayList<String> wordList;
}

