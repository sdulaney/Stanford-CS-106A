/* 
 * File: RobotFace.java
 * Name:
 * Section Leader:
 * --------------------
 * This program draws a robot face.
 */

import acm.program.*;
import acm.graphics.*;
import java.awt.*;

public class RobotFace extends GraphicsProgram {
	/* Parameters for the drawing */
	private static final int HEAD_WIDTH = 100;
	private static final int HEAD_HEIGHT = 150;
	private static final int EYE_RADIUS = 10;
	private static final int MOUTH_WIDTH = 60;
	private static final int MOUTH_HEIGHT = 20;
	
	public void run() {
		drawFace(getWidth() / 2, getHeight() / 2);
	}
	
	public void drawFace(double x, double y) {
		drawHead(x, y);
		drawEye(x - (0.25 * HEAD_WIDTH) - EYE_RADIUS, y - (0.25 * HEAD_HEIGHT) - EYE_RADIUS);
		drawEye(x + (0.25 * HEAD_WIDTH) - EYE_RADIUS, y - (0.25 * HEAD_HEIGHT) - EYE_RADIUS);
		drawMouth(x - MOUTH_WIDTH / 2, y + (0.25 * HEAD_HEIGHT) - (MOUTH_HEIGHT / 2));
	}
	
	public void drawHead(double x, double y) {
		x -= HEAD_WIDTH / 2;
		y -= HEAD_HEIGHT / 2;
		GRect head = new GRect(x, y, HEAD_WIDTH, HEAD_HEIGHT);
		head.setFilled(true);
		head.setFillColor(Color.GRAY);
		add(head);
	}
	
	public void drawEye(double x, double y) {
		GOval eye = new GOval(x, y, EYE_RADIUS * 2, EYE_RADIUS * 2);
		eye.setFilled(true);
		eye.setColor(Color.YELLOW);
		add(eye);
	}
	
	public void drawMouth(double x, double y) {
		GRect mouth = new GRect(x, y, MOUTH_WIDTH, MOUTH_HEIGHT);
		mouth.setFilled(true);
		mouth.setColor(Color.WHITE);
		add(mouth);
	}
}
