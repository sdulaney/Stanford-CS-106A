/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {	
	private static final double BOX_WIDTH = 150.0;
	private static final double BOX_HEIGHT = 50.0;
	public void run() {
		/* You fill this in. */
		drawProgramBox();
		drawConsoleBox();
		drawGraphicsBox();
		drawDialogBox();
		drawConsoleLine();
		drawGraphicsLine();
		drawDialogLine();
		
	}
	
	public void drawProgramBox() {
		double x = getWidth() / 2 - BOX_WIDTH / 2;
		double y = getHeight() / 2 - BOX_HEIGHT;
		GRect box = new GRect(x, y, BOX_WIDTH, BOX_HEIGHT);
		add(box);
		GLabel label = new GLabel("Program", x, y);
		add(label);
		label.move(BOX_WIDTH / 2 - label.getWidth() / 2, BOX_HEIGHT / 2 + label.getAscent() / 2);
	}
	
	public void drawConsoleBox() {
		double x = getWidth() / 2 - BOX_WIDTH / 2;
		double y = getHeight() / 2 + BOX_HEIGHT;
		GRect box = new GRect(x, y, BOX_WIDTH, BOX_HEIGHT);
		add(box);
		GLabel label = new GLabel("ConsoleProgram", x, y);
		add(label);
		label.move(BOX_WIDTH / 2 - label.getWidth() / 2, BOX_HEIGHT / 2 + label.getAscent() / 2);
	}
	
	public void drawGraphicsBox() {
		double x = getWidth() / 2 - BOX_WIDTH * 2;
		double y = getHeight() / 2 + BOX_HEIGHT;
		GRect box = new GRect(x, y, BOX_WIDTH, BOX_HEIGHT);
		add(box);
		GLabel label = new GLabel("GraphicsProgram", x, y);
		add(label);
		label.move(BOX_WIDTH / 2 - label.getWidth() / 2, BOX_HEIGHT / 2 + label.getAscent() / 2);
	}
	
	public void drawDialogBox() {
		double x = getWidth() / 2 + BOX_WIDTH;
		double y = getHeight() / 2 + BOX_HEIGHT;
		GRect box = new GRect(x, y, BOX_WIDTH, BOX_HEIGHT);
		add(box);
		GLabel label = new GLabel("DialogProgram", x, y);
		add(label);
		label.move(BOX_WIDTH / 2 - label.getWidth() / 2, BOX_HEIGHT / 2 + label.getAscent() / 2);
	}
	
	public void drawConsoleLine() {
		double x1 = getWidth() / 2;
		double y1 = getHeight() / 2;
		double x2 = getWidth() / 2;
		double y2 = getHeight() / 2 + BOX_HEIGHT;
		GLine line = new GLine(x1, y1, x2, y2);
		add(line);
	}
	
	public void drawGraphicsLine() {
		double x1 = getWidth() / 2;
		double y1 = getHeight() / 2;
		double x2 = getWidth() / 2 - BOX_WIDTH * 1.5;
		double y2 = getHeight() / 2 + BOX_HEIGHT;
		GLine line = new GLine(x1, y1, x2, y2);
		add(line);
	}

	public void drawDialogLine() {
		double x1 = getWidth() / 2;
		double y1 = getHeight() / 2;
		double x2 = getWidth() / 2 + BOX_WIDTH * 1.5;
		double y2 = getHeight() / 2 + BOX_HEIGHT;
		GLine line = new GLine(x1, y1, x2, y2);
		add(line);
	}
}

