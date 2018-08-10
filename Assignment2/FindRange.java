/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	private static final int sentinel = 0;
	public void run() {
		/* You fill this in */
		sayWelcome();
		findRange();
	}
	
	public void sayWelcome() {
		println("This program finds the largest and smallest numbers.");
	}
	
	public void findRange() {
		int inputNum = readInt("? ");
		if(inputNum == sentinel) {
			println("Please enter at least one number.");
		}
		int min = inputNum;
		int max = inputNum;
		while(inputNum != sentinel) {
			inputNum = readInt("? ");
			if(inputNum < min) {
				min = inputNum;
			}
			if(inputNum > max) {
				max = inputNum;
			}
		}
		println("smallest: " + min);
		println("largest: " + max);
	}
}

