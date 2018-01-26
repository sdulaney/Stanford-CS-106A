/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	/* Runs game sequence */
	private void playGame() {
		/* You fill this in */
		scorecard = new int[N_CATEGORIES][nPlayers];
		for (int turns = 0; turns < N_SCORING_CATEGORIES; turns++) {
			for(int player = 1; player <=  nPlayers; player++) {
				startFirstDiceRoll(player);
				secondAndThirdRoll();
				selectCategory(player);
			}
		}
		displayResults();
		calculateWinner();
	}
	
	/* Initializes first roll */
	private void startFirstDiceRoll(int player) {
		display.printMessage(playerNames[player - 1] + "'s turn! Click \"Roll Dice\" button to roll the dice.");
		display.waitForPlayerToClickRoll(player);
		for (int dice = 0; dice < N_DICE; dice++) {
			diceRoll[dice] = rgen.nextInt(1, 6);
		}
		display.displayDice(diceRoll);
	}
	
	/* Initializes 2nd and 3rd roll */
	private void secondAndThirdRoll() {
		for (int i = 0; i < 2; i++) {
			display.printMessage("Select the dice you wish to re-roll and click \"Roll Again\"");
			display.waitForPlayerToSelectDice();
			for(int dice = 0; dice < N_DICE; dice++) {
				if (display.isDieSelected(dice)) {
					diceRoll[dice] = rgen.nextInt(1, 6);
				}
			}
			display.displayDice(diceRoll);
		}		
	}
	
	/* Reads category selection */
	private void selectCategory(int player) {
		display.printMessage("Select a category for this roll.");
		while (true) {
			int category = display.waitForPlayerToSelectCategory();
			if(selectedCategories[player][category] == 0 && YahtzeeMagicStub.checkCategory(diceRoll, category)) {
				calculateCategoryScore(player, category);
				break;
			}
			display.printMessage("You have already selected this category. Please select a new category.");
		}
	}
	
	/* Calculates turn score based on category selection */
	private void calculateCategoryScore(int player, int category) {
		
	}
	
	/* Calculates a player's total scores */
	private void calculateScores(int player) {
		
	}
	
	/* Updates score totals for all players on the scorecard */
	private void displayResults() {
		
	}
	
	/* Determines the winner of the game */
	private void calculateWinner() {
		
	}
	
	/* Returns true if the values of the dice stored in the array are valid for the category and false otherwise */
	private boolean checkCategory(int[] dice, int category) {
		
		return false;
	}
		
/* Private instance variables */
	private int[][] scorecard;
	private int[][] selectedCategories;
	private int[] diceRoll = new int[N_DICE];
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();

}
