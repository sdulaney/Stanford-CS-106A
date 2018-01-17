/*
 * File: Hangman.java
 * -------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {
	
	public void run() {
		setUpGame();
		playGame();
	}
	
	private void setUpGame() {
		hiddenWord = getMaskedWord();
		println("Welcome to Hangman!");
		println("The word now looks like this: " + hiddenWord);
		println("You have " + guessCounter + " guesses left.");
	}
	
	private String getWord() {
		hangmanWords = new HangmanLexicon();
		int index = rgen.nextInt(0, hangmanWords.getWordCount() - 1);
		String word = hangmanWords.getWord(index);
		return word;
	}
	
	private String getMaskedWord() {
		String result = "";
		for(int i = 0; i < word.length(); i++) {
			result = result + "-";
		}
		return result;
	}
	
	private void playGame() {
		while(guessCounter > 0) {
			String input = readLine("Your guess: ");
			while(true) {
				if(input.length() > 1) {
					input = readLine("You can only guess one letter at a time. Try again: ");
				}
				if(input.length() == 1) {
					break;
				}
			}
			ch = input.charAt(0);
			if(Character.isLowerCase(ch)) {
				ch = Character.toUpperCase(ch);
			}
			letterCheck();
			if(hiddenWord.equals(word)) {
				println("You guessed the word: " + word);
				println("You win");
				break;
			}
			println("The word now looks like this: " + hiddenWord);
			println("You have " + guessCounter + " guesses left.");
		}
		if(guessCounter == 0) {
			println("You're completely hung.");
			println("The word was: " + word);
			println("You lose.");
		}
	}
	
	/** Updates the hiddenWord if the character entered is correct */
	private void letterCheck() {
		if(word.indexOf(ch) == -1) {
			println("There are no " + ch + "'s in the word");
			guessCounter--;
			incorrectLetters = incorrectLetters + ch;
		}
		else {
			println("The guess is correct.");
			for(int i = 0; i < word.length(); i++) {
				if(ch == word.charAt(i)) {
					if(i > 0) {
						hiddenWord = hiddenWord.substring(0, i) + ch + hiddenWord.substring(i + 1);
					}
					else if(i == 0) {
						hiddenWord = ch + hiddenWord.substring(1);
					}
				}
			}
		}
	}
	
	private HangmanLexicon hangmanWords;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private int guessCounter = 8;
	private String hiddenWord;
	private String word = getWord();
	private char ch;
	private String incorrectLetters = "";
}
