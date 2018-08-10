/* File: InnerBorderKarel.java */

import stanford.karel.*;

public class InnerBorderKarel extends SuperKarel {

	public void run() {
		moveUpRow();
		for(int i = 0; i < 4; i++) {
			handleBorder();
			nextPosition();
		}
	}
	
	// Assumes Karel starts one avenue before the first beeper to
	// be placed in this line of the border. Places beepers until
	// Karel reaches a wall, but does not place a beeper on the last
	// corner (where Karel is facing the wall).
	private void handleBorder() {
		move();
		while (frontIsClear()) {
			// We check for any existing beepers, so we don't put
			// two beepers on any of the "corners" of the border
			if (noBeepersPresent()) {
				putBeeper();
			}
			move();
		}
	}
	
	// Moves Karel up one row while keeping the same orientation
	private void moveUpRow() {
		turnLeft();
		move();
		turnRight();
	}
	
	// Assumes Karel is facing a wall at the end of line of placed
	// beepers and repositions Karel to be facing in direction of next
	// line in the border of beepers that need to be placed
	private void nextPosition() {
		turnRight();
		move();
		turnRight();
		move();
		turnRight();
	}
}
