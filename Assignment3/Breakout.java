/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;

/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		/* You fill this in, along with any subsidiary methods */
		setUpGame();
		playGame();
	}
	
	public void setUpGame() {
		drawBricks();
	}
	
	public void playGame() {
		
	}
	
	public void drawBricks() {
		for(int i = 0; i < NBRICK_ROWS; i++) {
			for(int j = 0; j < NBRICKS_PER_ROW; j++) {
				int x = getWidth() / 2 - (NBRICKS_PER_ROW * BRICK_WIDTH) / 2 - ((NBRICKS_PER_ROW - 1) * BRICK_SEP) / 2 + (j * (BRICK_WIDTH + BRICK_SEP));
				int y = BRICK_Y_OFFSET + ((BRICK_HEIGHT + BRICK_SEP) * i);
				GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
				Color brickColor = Color.RED;
				switch(i) {
					case 0: break;
					case 1: break;
					case 2: brickColor = Color.ORANGE;
							break;
					case 3: brickColor = Color.ORANGE;
							break;
					case 4: brickColor = Color.YELLOW;
							break;
					case 5: brickColor = Color.YELLOW;
							break;
					case 6: brickColor = Color.GREEN;
							break;
					case 7: brickColor = Color.GREEN;
							break;
					case 8: brickColor = Color.CYAN;
							break;
					case 9: brickColor = Color.CYAN;
							break;	
				}
				brick.setColor(brickColor);
				brick.setFillColor(brickColor);
				brick.setFilled(true);
				add(brick);
			}
		}
	}

}
