package com.cpa.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.cpa.algorithms.ConvexHull;
import com.cpa.algorithms.EnclosingRectangle;
import com.cpa.algorithms.MinimumCircle;
import com.cpa.geometry.Circle;
import com.cpa.geometry.Vertex;
import com.cpa.tools.TestFilesManager;

@SuppressWarnings("serial")
public class CanvasPanel extends JPanel {

	ArrayList<Vertex> set;
	ArrayList<Vertex> convexHull;
	ArrayList<Vertex> rectangle;
	Circle circle;
	long timeHull, timeRectangle, timeCircle;

	double zoomFactor;
	AffineTransform transform;

	public CanvasPanel() {
		TestFilesManager tfm = TestFilesManager.getInstance();
		set = tfm.getNextFile();
		convexHull = new ArrayList<Vertex>();
		rectangle = new ArrayList<Vertex>();
		timeHull = 0;
		timeRectangle = 0;
		timeCircle = 0;
		transform = new AffineTransform();
		zoomFactor = 1;
		requestFocus();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
		setBackground(Color.BLACK);
	}

	private void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setTransform(transform);
		for (Vertex v : set) {
			drawPoint(v, g2, Color.WHITE);
		}

		drawHull(g2, Color.RED);
		drawRectangle(g2, Color.GREEN);
		drawCircle(g2, Color.PINK);
	}

	private void drawPoint(Vertex p, Graphics g, Color color) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
		//    g2.drawLine(p.x, p.y, p.x, p.y);
		g2.drawRect((int)p.x - 1, (int)p.y - 1, 3, 3);
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
					(int)convexHull.get(i).x,(int) convexHull.get(i).y);
			drawPoint(convexHull.get(i), g2, color);
		}
		g2.drawLine((int)convexHull.get(i - 1).x,(int) convexHull.get(i - 1).y,
				(int) convexHull.get(0).x, (int) convexHull.get(0).y);

		if (timeHull > 0) {
			String message = "Convex Hull computed in " + Long.toString(timeHull)
					+ " ms.";
			g2.setColor(Color.WHITE);
			g2.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 13));
			g2.drawChars(message.toCharArray(), 0, message.length(), 30, 30);
		}

	}

	private void drawRectangle(Graphics g, Color color) {

		if (rectangle.size() == 0)
			return;
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
		int i;
		drawPoint(rectangle.get(0), g2, color);
		for (i = 1; i < rectangle.size(); i++) {
			g2.drawLine((int)rectangle.get(i - 1).x, (int)rectangle.get(i - 1).y,
					(int)rectangle.get(i).x,(int) rectangle.get(i).y);
			drawPoint(rectangle.get(i), g2, color);
		}
		g2.drawLine((int)rectangle.get(i - 1).x,(int) rectangle.get(i - 1).y,
				(int)rectangle.get(0).x, (int)rectangle.get(0).y);

		if (timeRectangle > 0) {
			String message = "Rectangle computed in " + Long.toString(timeRectangle)
					+ " ms.";
			g2.setColor(Color.WHITE);
			g2.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 13));
			g2.drawChars(message.toCharArray(), 0, message.length(), 30, 60);
		}

	}

	private void drawCircle(Graphics g, Color color) {
		if (circle == null)
			return;
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
		g2.drawOval((int)circle.center.x - (int) circle.radius, (int)circle.center.y
				- (int) circle.radius, (int) (2 * circle.radius),
				(int) (2 * circle.radius));

		if (timeCircle > 0) {
			String message = "Circle computed in " + Long.toString(timeCircle)
					+ " ms.";
			g2.setColor(Color.WHITE);
			g2.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 13));
			g2.drawChars(message.toCharArray(), 0, message.length(), 30, 60);
		}

	}

	public void nextTest() {
		TestFilesManager tfm = TestFilesManager.getInstance();
		set = tfm.getNextFile();
		convexHull = new ArrayList<Vertex>();
		rectangle = new ArrayList<Vertex>();
		circle = null;
		timeHull = 0;
		timeRectangle = 0;
		timeCircle = 0;
		zoomFactor = 1;
		repaint();
	}

	public void generateConvexHull() {
		long start = System.currentTimeMillis();
		convexHull = ConvexHull.graham(ConvexHull.pixelSort2(set));
		timeHull = System.currentTimeMillis() - start;
		repaint();
	}

	public void generateRectangle() {
		if (convexHull.size() == 0)
			return;
		long start = System.currentTimeMillis();
		rectangle = EnclosingRectangle.computeToussaint(convexHull);
		timeRectangle = System.currentTimeMillis() - start;
		repaint();
	}

	public void generateCircle() {
		long start = System.currentTimeMillis();
		circle = MinimumCircle.computeRitter(set);
		timeCircle = System.currentTimeMillis() - start;
		repaint();

	}

	public void zoomIn() {
		zoomFactor *= 1.1;
		transform = new AffineTransform();
		transform.scale(zoomFactor, zoomFactor);
		repaint();
	}

	public void zoomOut() {
		zoomFactor *= 0.9;
		if (zoomFactor == 0)
			zoomFactor = 1;
		transform = new AffineTransform();
		transform.scale(zoomFactor, zoomFactor);
		repaint();
	}

}
