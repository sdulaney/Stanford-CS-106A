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
		totalUpResults();
		calculateWinner();
	}
	
	private void startFirstDiceRoll(int player) {
		display.printMessage(playerNames[player - 1] + "'s turn! Click \"Roll Dice\" button to roll the dice.");
		display.waitForPlayerToClickRoll(player);
		for (int dice = 0; dice < N_DICE; dice++) {
			diceRoll[dice] = rgen.nextInt(1, 6);
		}
		display.displayDice(diceRoll);
	}
	
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
	
	private void selectCategory(int player) {
		
	}
	
	private void totalUpResults() {
		
	}
	
	private void calculateWinner() {
		
	}
		
/* Private instance variables */
	private int[][] scorecard;
	private int[] diceRoll = new int[N_DICE];
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();

}
