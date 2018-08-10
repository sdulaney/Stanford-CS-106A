/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
		entryGraph = new ArrayList<NameSurferEntry>();
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		entryGraph.clear();
		update();
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		entryGraph.add(entry);
	}
	
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		removeAll();
		drawGraph();
		
		for (int i = 0; i < entryGraph.size(); i++) {
			drawEntry(entryGraph.get(i), i);
		}
	}
	
	
	
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
	
	private void drawGraph() {
		drawHorizLines();
		drawVertLines();
		drawDateLabels();
	}
	
	private void drawHorizLines() {
		GLine topLine = new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);
		add(topLine);
		GLine botLine = new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE);
		add(botLine);
	}
	
	private void drawVertLines() {
		for (int i = 0; i < NDECADES; i++) {
			GLine vertLine = new GLine((i + 1) * (getWidth() / NDECADES), 0, (i + 1) * (getWidth() / NDECADES), getHeight());
			add(vertLine);
		}
	}
	
	private void drawDateLabels() {
		for (int i = 0; i < NDECADES; i++) {
			String dateString = Integer.toString(i * 10 + START_DECADE);
			GLabel dateLabel = new GLabel(dateString, i * (getWidth() / NDECADES), getHeight());
			add(dateLabel);
		}
	}
	
	private void drawEntry(NameSurferEntry entry, int color) {
		for (int i = 0; i < NDECADES - 1; i++) {
			GLine line = new GLine((getWidth() / NDECADES) * i, vertValue(entry.getRank(i)), (getWidth() / NDECADES) * (i + 1), vertValue(entry.getRank(i + 1)));
			if (color % 4 == 0) {
				line.setColor(Color.BLACK);
			} else if (color % 4 == 1) {
				line.setColor(Color.RED);
			} else if (color % 4 == 2) {
				line.setColor(Color.BLUE);
			} else if (color % 4 == 3) {
				line.setColor(Color.MAGENTA);
			}
			add(line);
		}
		for (int i = 0; i < NDECADES; i++) {
			String labelEntry = "";
			if (entry.getRank(i) != 0) {
				labelEntry = entry.getName() + " " + entry.getRank(i);
			} else {
				labelEntry = entry.getName() + " *";
			}
			GLabel nameLabel = new GLabel(labelEntry, (getWidth() / NDECADES) * i, vertValue(entry.getRank(i)));
			if (color % 4 == 0) {
				nameLabel.setColor(Color.BLACK);
			} else if (color % 4 == 1) {
				nameLabel.setColor(Color.RED);
			} else if (color % 4 == 2) {
				nameLabel.setColor(Color.BLUE);
			} else if (color % 4 == 3) {
				nameLabel.setColor(Color.MAGENTA);
			}
			add(nameLabel);
		}
	}
	
	private double vertValue(int rank) {
		double result = rank;
		if (rank != 0) {
			result /= MAX_RANK;
			result *= (getHeight() - 2 * GRAPH_MARGIN_SIZE);
			result += GRAPH_MARGIN_SIZE;
		} else {
			result = getHeight() - GRAPH_MARGIN_SIZE;
		}
		return result;
	}
	
	/* Private instance variables */
	private ArrayList<NameSurferEntry> entryGraph;
}
