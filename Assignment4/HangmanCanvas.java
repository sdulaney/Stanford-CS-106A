/*
 * File: HangmanCanvas.java
 * -------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

	/** Resets the display so that only the scaffold appears */
	public void reset() {
		/* You fill this in */
		drawScaffold();
	}
	
	/** Updates the word on the screen to correspond to the current
	 * state of the game. The argument string shows what letters have
	 * been guessed so far; unguessed letters are indicated by hyphens.
	 */
	public void displayWord(String word) {
		/* You fill this in */
		double x = getWidth() / 4;
		double y = getHeight() - (getHeight() - SCAFFOLD_HEIGHT) / 4;
		GLabel hiddenWord = new GLabel(word, x, y);
		hiddenWord.setFont("Helvetica-24");
		if(getElementAt(x, y) != null) {
			remove(getElementAt(x, y));
		}
		add(hiddenWord);
	}
	
	/**
	 * Updates the display to correspond to an incorrect guess by the
	 * user. Calling this method causes the next body part to appear
	 * on the scaffold and adds the letter to the list of incorrect
	 * guesses that appears at the bottom of the window.
	 */
	public void noteIncorrectGuess(String incorrectGuesses) {
		/* You fill this in */
		double x = getWidth() / 4;
		double y = getHeight() - (getHeight() - SCAFFOLD_HEIGHT) / 8;
		GLabel incorrectLetters = new GLabel(incorrectGuesses, x, y);
		if(getElementAt(x, y) != null) {
			remove(getElementAt(x, y));
		}
		add(incorrectLetters);
		int length = incorrectGuesses.length();
		switch(length) {
			case 1:	drawHead();
					break;
			case 2:	drawBody();
					break;
			case 3:	drawLeftArm();
					break;
			case 4:	drawRightArm();
					break;
			case 5:	drawLeftLeg();
					break;
			case 6:	drawRightLeg();
					break;
			case 7:	drawLeftFoot();
					break;
			case 8:	drawRightFoot();
					break;
		}
	}
	
	private void drawScaffold() {
		double x1 = getWidth() / 2 - BEAM_LENGTH;
		double y1 = getHeight() / 2 - SCAFFOLD_HEIGHT / 2;
		double y2 = y1 + SCAFFOLD_HEIGHT;
		GLine scaffold = new GLine(x1, y1, x1, y2);
		add(scaffold);
		double x2 = x1 + BEAM_LENGTH;
		GLine beam = new GLine(x1, y1, x2, y1);
		add(beam);
		y2 = y1 + ROPE_LENGTH;
		GLine rope = new GLine(x2, y1, x2, y2);
		add(rope);
	}
	
	private void drawHead() {
		double x = getWidth() / 2 - HEAD_RADIUS;
		double y = getHeight() / 2 - SCAFFOLD_HEIGHT / 2 + ROPE_LENGTH;
		GOval head = new GOval(x, y, 2 * HEAD_RADIUS, 2 * HEAD_RADIUS);
		add(head);
	}
	
	private void drawBody() {
		double x1 = getWidth() / 2;
		double y1 = getHeight() / 2 - SCAFFOLD_HEIGHT / 2 + ROPE_LENGTH + 2 * HEAD_RADIUS;
		double y2 = y1 + BODY_LENGTH;
		GLine body = new GLine(x1, y1, x1, y2);
		add(body);
	}
	
	private void drawLeftArm() {
		double x1 = getWidth() / 2 - UPPER_ARM_LENGTH;
		double y1 = getHeight() / 2 - SCAFFOLD_HEIGHT / 2 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD;
		double x2 = getWidth() / 2;
		GLine upperArm = new GLine(x1, y1, x2, y1);
		add(upperArm);
		double y2 = y1 + LOWER_ARM_LENGTH;
		GLine lowerArm = new GLine(x1, y1, x1, y2);
		add(lowerArm);
	}
	
	private void drawRightArm() {
		double x1 = getWidth() / 2;
		double y1 = getHeight() / 2 - SCAFFOLD_HEIGHT / 2 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD;
		double x2 = getWidth() / 2 + UPPER_ARM_LENGTH;
		GLine upperArm = new GLine(x1, y1, x2, y1);
		add(upperArm);
		double y2 = y1 + LOWER_ARM_LENGTH;
		GLine lowerArm = new GLine(x2, y1, x2, y2);
		add(lowerArm);
	}
	
	private void drawLeftLeg() {
		double x1 = getWidth() / 2 - HIP_WIDTH;
		double y1 = getHeight() / 2 - SCAFFOLD_HEIGHT / 2 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH;
		double x2 = getWidth() / 2;
		GLine upperLeg = new GLine(x1, y1, x2, y1);
		add(upperLeg);
		double y2 = y1 + LEG_LENGTH;
		GLine lowerLeg = new GLine(x1, y1, x1, y2);
		add(lowerLeg);
	}
	
	private void drawRightLeg() {
		double x1 = getWidth() / 2;
		double y1 = getHeight() / 2 - SCAFFOLD_HEIGHT / 2 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH;
		double x2 = getWidth() / 2 + HIP_WIDTH;
		GLine upperLeg = new GLine(x1, y1, x2, y1);
		add(upperLeg);
		double y2 = y1 + LEG_LENGTH;
		GLine lowerLeg = new GLine(x2, y1, x2, y2);
		add(lowerLeg);
	}
	
	private void drawLeftFoot() {
		double x1 = getWidth() / 2 - HIP_WIDTH - FOOT_LENGTH;
		double y1 = getHeight() / 2 - SCAFFOLD_HEIGHT / 2 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH;
		double x2 = getWidth() / 2 - HIP_WIDTH;
		GLine foot = new GLine(x1, y1, x2, y1);
		add(foot);
	}
	
	private void drawRightFoot() {
		double x1 = getWidth() / 2 + HIP_WIDTH;
		double y1 = getHeight() / 2 - SCAFFOLD_HEIGHT / 2 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH;
		double x2 = getWidth() / 2 + HIP_WIDTH + FOOT_LENGTH;
		GLine foot = new GLine(x1, y1, x2, y1);
		add(foot);
	}
	
	/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
}
