/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;
import java.util.*;

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
		selectedCategories = new int[N_CATEGORIES][nPlayers];
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
			// diceRoll[dice] = rgen.nextInt(1, 6);
			// Cheat mode
			IODialog dialog = getDialog();
			int num = dialog.readInt("Enter number for die");
			diceRoll[dice] = num;
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
			if (selectedCategories[category - 1][player - 1] == 0) {
				calculateCategoryScore(player, category);
				break;
			}
			display.printMessage("You have already selected this category. Please select a new category.");
		}
	}
	
	/* Calculates turn score based on whether dice roll is valid for given category */
	private void calculateCategoryScore(int player, int category) {
		selectedCategories[category - 1][player - 1] = 1;
		int score;
		int totalScore;
		if (checkCategory(diceRoll, category)) {
			setCategoryScore(player, category);
			score = scorecard[category - 1][player - 1];
			display.updateScorecard(category,  player,  score);
			calculateScores(player);
			totalScore = scorecard[TOTAL - 1][player - 1];
			display.updateScorecard(TOTAL,  player, totalScore);
		}
		else {
			scorecard[category - 1][player - 1] = 0;
			display.updateScorecard(category,  player, 0);
			calculateScores(player);
			totalScore = scorecard[TOTAL - 1][player - 1];
			display.updateScorecard(TOTAL, player, totalScore);
		}
	}
	
	/* Awards player points for given category */
	private void setCategoryScore(int player, int category) {
		int score = 0;
		if (category >= ONES && category <= SIXES) {
			for (int i = 0; i < N_DICE; i++) {
				if(diceRoll[i] == category) {
					score += diceRoll[i];
				}
			}
		}
		else if (category == THREE_OF_A_KIND || category == FOUR_OF_A_KIND || category == CHANCE) {
			for (int i = 0; i < N_DICE; i++) {
				score += diceRoll[i];
			}
		}
		else if (category == FULL_HOUSE) {
			score = 25;
		}
		else if (category == SMALL_STRAIGHT) {
			score = 30;
		}
		else if (category == LARGE_STRAIGHT) {
			score = 40;
		}
		else if (category == YAHTZEE) {
			score = 50;
		}
		scorecard[category - 1][player - 1] = score;
	}
	
	/* Calculates a player's total scores */
	private void calculateScores(int player) {
		int upperScore = 0;
		int lowerScore = 0;
		int totalScore = 0;
		for (int i = ONES; i <= SIXES; i++) {
			upperScore += scorecard[i - 1][player - 1];
		}
		for(int i = THREE_OF_A_KIND; i <= CHANCE; i++) {
			lowerScore += scorecard[i - 1][player - 1];
		}
		totalScore = upperScore + lowerScore;
		scorecard[UPPER_SCORE - 1][player - 1] = upperScore;
		scorecard[LOWER_SCORE - 1][player - 1] = lowerScore;
		scorecard[TOTAL - 1][player - 1] = totalScore;
	}
	
	/* Updates score totals for all players on the scorecard */
	private void displayResults() {
		for(int i = 0; i < nPlayers; i++) {
			display.updateScorecard(UPPER_SCORE, i + 1, scorecard[UPPER_SCORE - 1][i]);
			display.updateScorecard(LOWER_SCORE, i + 1, scorecard[LOWER_SCORE - 1][i]);
			if(scorecard[UPPER_SCORE - 1][i] >= 63) {
				scorecard[UPPER_BONUS - 1][i] = 35;
			}
			display.updateScorecard(UPPER_BONUS, i + 1, scorecard[UPPER_BONUS - 1][i]);
			scorecard[TOTAL - 1][i] = scorecard[TOTAL - 1][i] + scorecard[UPPER_BONUS - 1][i];
			display.updateScorecard(TOTAL, i + 1, scorecard[TOTAL - 1][i]);
		}
	}
	
	/* Determines the winner of the game */
	private void calculateWinner() {
		int score = 0;
		int winner = 0;
		for (int i = 0; i < nPlayers; i++) {
			int x = scorecard[TOTAL - 1][i];
			if (x > score) {
				score = x;
				winner = i;
			}
		}
		display.printMessage("Congratulations, " + playerNames[winner] + ", you're the winner with a total score of " + score + "!");
	}
	
	/* Returns true if the values of the dice stored in the array are valid for the category and false otherwise */
	private boolean checkCategory(int[] dice, int category) {
		
		if (category == ONES || category == TWOS || category == THREES || category == FOURS || category == FIVES || category == SIXES || category == CHANCE) {
			return true;
		}
		
		ArrayList <Integer> one = new ArrayList<Integer>();
		ArrayList <Integer> two = new ArrayList<Integer>();
		ArrayList <Integer> three = new ArrayList<Integer>();
		ArrayList <Integer> four = new ArrayList<Integer>();
		ArrayList <Integer> five = new ArrayList<Integer>();
		ArrayList <Integer> six = new ArrayList<Integer>();
		
		for (int i = 0; i < N_DICE; i++) {
			if (diceRoll[i] == 1) {
				one.add(1);
			}
			else if (diceRoll[i] == 2) {
				two.add(1);
			}
			else if (diceRoll[i] == 3) {
				three.add(1);
			}
			else if (diceRoll[i] == 4) {
				four.add(1);
			}
			else if (diceRoll[i] == 5) {
				five.add(1);
			}
			else if (diceRoll[i] == 6) {
				six.add(1);
			}
		}
		
		if (category == THREE_OF_A_KIND) {
			if (one.size() >= 3 || two.size() >= 3 || three.size() >= 3 || four.size() >= 3 || five.size() >= 3 || six.size() >= 3) {
				return true;
			}
		}
		else if (category == FOUR_OF_A_KIND) {
			if (one.size() >= 4 || two.size() >= 4 || three.size() >= 4 || four.size() >= 4 || five.size() >= 4 || six.size() >= 4) {
				return true;
			}
		}
		else if (category == FULL_HOUSE) {
			if (one.size() == 3 || two.size() == 3 || three.size() == 3 || four.size() == 3 || five.size() == 3 || six.size() == 3) {
				if (one.size() == 2 || two.size() == 2 || three.size() == 2 || four.size() == 2 || five.size() == 2 || six.size() == 2) {
					return true;
				}
			}
		}
		else if (category == SMALL_STRAIGHT) {
			if (one.size() >= 1 && two.size() >= 1 && three.size() >= 1 && four.size() >= 1) {
				return true;
			} 
			else if (two.size() >= 1 && three.size() >= 1 && four.size() >= 1 && five.size() >= 1) {
				return true;
			}
			else if (three.size() >= 1 && four.size() >= 1 && five.size() >= 1 && six.size() >= 1) {
				return true;
			}
		}
		else if (category == LARGE_STRAIGHT) {
			if (one.size() == 1 && two.size() == 1 && three.size() == 1 && four.size() == 1 && five.size() == 1) {
				return true;
			}
			else if (two.size() == 1 && three.size() == 1 && four.size() == 1 && five.size() == 1 && six.size() == 1) {
				return true;
			}
		}
		else if (category == YAHTZEE) {
			if (one.size() == 5 || two.size() == 5 || three.size() == 5 || four.size() == 5 || five.size() == 5 || six.size() == 5) {
				return true;
			}
		}
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
