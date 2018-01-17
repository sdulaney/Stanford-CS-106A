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
	private static final int BRICK_WIDTH = (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 3;
	
	/** Animation delay or pause time between ball moves */
	private static final int DELAY = 10;
	
	/** Number of bricks remaining */
	private int brickCounter = 100;

	/* Method: run() */
	/** Runs the Breakout program. */
	public void run() {
		/* You fill this in, along with any subsidiary methods */
		for(int i = 0; i < NTURNS; i++) {
			setUpGame();
			playGame();
			if(brickCounter == 0) {
				ball.setVisible(false);
				printWinner();
				break;
			}
			else if (brickCounter > 0) {
				removeAll();
			}
		}
		if(brickCounter > 0) {
			printGameOver();
		}
	}
	
	public void setUpGame() {
		drawBricks();
		drawPaddle();
		drawBall();
	}
	
	public void playGame() {
		getBallVelocity();
		while(true) {
			moveBall();
			if(ball.getY() >= getHeight()) {
				break;
			}
			if(brickCounter == 0) {
				break;
			}
		}
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
	
	private GRect paddle;
	
	public void drawPaddle() {
		int x = getWidth() / 2 - PADDLE_WIDTH / 2;
		int y = getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT / 2;
		paddle = new GRect(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		add(paddle);
		addMouseListeners();
	}
	
	public void mouseMoved(MouseEvent e) {
		if(e.getX() < getWidth() - PADDLE_WIDTH / 2 && e.getX() > PADDLE_WIDTH / 2) {
			paddle.setLocation(e.getX() - PADDLE_WIDTH / 2, getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT / 2);
		}
	}
	
	private double vx, vy;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private GOval ball;
	
	public void drawBall() {
		int x = getWidth() / 2;
		int y = getHeight() / 2;
		ball = new GOval(x, y, 2 * BALL_RADIUS, 2 * BALL_RADIUS);
		ball.setFilled(true);
		add(ball);
	}
	
	public void getBallVelocity() {
		vx = rgen.nextDouble(1.0, 3.0);
		if(rgen.nextBoolean(0.5)) {
			vx = -vx;
		}
		vy = 3.0;
	}
	
	public void moveBall() {
		ball.move(vx,  vy);
		// Check for side walls
		if((ball.getX() - vx <= 0 && vx < 0) || (ball.getX() + vx >= (getWidth() - 2 * BALL_RADIUS) && vx > 0)) {
			vx = -vx;
		}
		// Check for top wall
		if(ball.getY() - vy <= 0 && vy < 0) {
			vy = -vy;
		}
		
		GObject collider = getCollidingObject();
		if(collider == paddle) {
			vy = -vy;
		}
		else if(collider != null) {
			remove(collider);
			brickCounter--;
			vy = -vy;
		}
		pause(DELAY);
	}
	
	private GObject getCollidingObject() {
		if(getElementAt(ball.getX(), ball.getY()) != null) {
			return getElementAt(ball.getX(), ball.getY());
		}
		else if(getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY()) != null) {
			return getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY());
		}
		else if(getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS) != null) {
			return getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS);
		}
		else if(getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS) != null) {
			return getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS);
		}
		return null;
	}
	
	private void printGameOver() {
		GLabel label = new GLabel("Game Over", getWidth() / 2, getHeight() / 2);
		label.move(-label.getWidth() / 2, -label.getHeight() / 2);
		label.setColor(Color.RED);
		add(label);
	}
	
	private void printWinner() {
		GLabel label = new GLabel("Winner", getWidth() / 2, getHeight() / 2);
		label.move(-label.getWidth() / 2, -label.getHeight() / 2);
		label.setColor(Color.RED);
		add(label);
	}

}
