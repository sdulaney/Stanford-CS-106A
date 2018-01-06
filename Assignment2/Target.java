/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	public void run() {
		/* You fill this in. */
		drawCircle(72, Color.RED);
		drawCircle(0.65 * 72, Color.WHITE);
		drawCircle(0.3 * 72, Color.RED);
	}
	
	public void drawCircle(double radius, Color c) {
		double x = getWidth() / 2 - radius / 2;
		double y = getHeight() / 2 - radius / 2;
		GOval circle = new GOval(radius, radius);
		circle.setFilled(true);
		circle.setFillColor(c);
		circle.move(x, y);
		add(circle);
	}
}
