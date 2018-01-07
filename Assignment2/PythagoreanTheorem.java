/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	public void run() {
		/* You fill this in */
		sayWelcome();
		calculateHypotenuse();	
	}
	
	public void sayWelcome() {
		println("Enter values to compute Pythagorean theorem.");
	}
	
	public void calculateHypotenuse() {
		int a = readInt("a: ");
		int b = readInt("b: ");
		double c = Math.sqrt((a * a) + (b * b));
		println("c: " + c);
	}
}
