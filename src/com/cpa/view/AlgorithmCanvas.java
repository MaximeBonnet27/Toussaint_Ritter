package com.cpa.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.cpa.algorithms.ConvexHull;
import com.cpa.algorithms.EnclosingRectangle;
import com.cpa.geometry.GeometryTools;
import com.cpa.geometry.Line;
import com.cpa.geometry.Rectangle;
import com.cpa.geometry.Vector;
import com.cpa.geometry.Vertex;
import com.cpa.tools.TestFilesManager;

public class AlgorithmCanvas extends JPanel{

	static ArrayList<Vertex> set;
	static ArrayList<Vertex> convexHull;
	public static ArrayList<Vertex> rectangle;

	public AlgorithmCanvas() {
		this.set = new ArrayList<>();
		this.convexHull = new ArrayList<>();
		this.rectangle = new ArrayList<>();
	}
	
	public void nextTest() {
		TestFilesManager tfm = TestFilesManager.getInstance();
		set = tfm.getNextFile();
		convexHull = new ArrayList<Vertex>();
		rectangle = new ArrayList<Vertex>();
		repaint();
	}
	
	public void generateConvexHull() {
		long start = System.currentTimeMillis();
		convexHull = ConvexHull.graham(ConvexHull.pixelSort2(set));
		repaint();
	}

	public void generateRectangle() {
		if (convexHull.size() == 0)
			return;
		long start = System.currentTimeMillis();
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
		setBackground(Color.BLACK);
	}

	private void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		for (Vertex v : set) {
			drawPoint(v, g2, Color.WHITE);
		}

		drawHull(g2, Color.RED);
		drawRectangle(g2, Color.GREEN);
	}

	private void drawPoint(Vertex p, Graphics g, Color color) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
		//    g2.drawLine(p.x, p.y, p.x, p.y);
		g2.drawRect((int)p.x - 1,(int) p.y - 1, 3, 3);
	}

	private void drawHull(Graphics g, Color color) {
		if (convexHull.size() == 0)
			return;
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
		int i;
		drawPoint(convexHull.get(0), g2, color);
		for (i = 1; i < convexHull.size(); i++) {
			g2.drawLine((int)convexHull.get(i - 1).x,(int) convexHull.get(i - 1).y,
					(int)	convexHull.get(i).x,(int) convexHull.get(i).y);
			drawPoint(convexHull.get(i), g2, color);
		}
		g2.drawLine((int)convexHull.get(i - 1).x, (int)convexHull.get(i - 1).y,
				(int)convexHull.get(0).x, (int)convexHull.get(0).y);


	}

	private void drawRectangle(Graphics g, Color color) {

		if (rectangle.size() == 0)
			return;
		System.out.println("DRAWING");
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
		int i;
		drawPoint(rectangle.get(0), g2, color);
		for (i = 1; i < rectangle.size(); i++) {
			g2.drawLine((int) rectangle.get(i - 1).x,(int) rectangle.get(i - 1).y,
					(int)rectangle.get(i).x,(int) rectangle.get(i).y);
			drawPoint(rectangle.get(i), g2, color);
		}
		g2.drawLine((int)rectangle.get(i - 1).x, (int)rectangle.get(i - 1).y,
				(int)rectangle.get(0).x,(int) rectangle.get(0).y);


	}

	public static void setRectangle(ArrayList<Vertex> r){
		rectangle = r;
	}
	
	
}
