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

	ArrayList<Vertex> set;
	ArrayList<Vertex> convexHull;
	ArrayList<Vertex> rectangle;

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
		setRectangle(convexHull);
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
		g2.drawRect(p.x - 1, p.y - 1, 3, 3);
	}

	private void drawHull(Graphics g, Color color) {
		if (convexHull.size() == 0)
			return;
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
		int i;
		drawPoint(convexHull.get(0), g2, color);
		for (i = 1; i < convexHull.size(); i++) {
			g2.drawLine(convexHull.get(i - 1).x, convexHull.get(i - 1).y,
					convexHull.get(i).x, convexHull.get(i).y);
			drawPoint(convexHull.get(i), g2, color);
		}
		g2.drawLine(convexHull.get(i - 1).x, convexHull.get(i - 1).y,
				convexHull.get(0).x, convexHull.get(0).y);


	}

	private void drawRectangle(Graphics g, Color color) {

		if (rectangle.size() == 0)
			return;
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
		int i;
		drawPoint(rectangle.get(0), g2, color);
		for (i = 1; i < rectangle.size(); i++) {
			g2.drawLine(rectangle.get(i - 1).x, rectangle.get(i - 1).y,
					rectangle.get(i).x, rectangle.get(i).y);
			drawPoint(rectangle.get(i), g2, color);
		}
		g2.drawLine(rectangle.get(i - 1).x, rectangle.get(i - 1).y,
				rectangle.get(0).x, rectangle.get(0).y);


	}

	public void setRectangle(ArrayList<Vertex> hull){

		// 1: Find the 4 extreme points.

				int index_i = 0, index_j = 0, index_k = 0, index_l = 0;
				int index_i0, index_j0, index_k0, index_l0;
				for (int i = 1; i < hull.size(); i++) {
					if (hull.get(i).x < hull.get(index_i).x) {
						index_i = i;
					}
					if (hull.get(i).y < hull.get(index_j).y) {
						index_j = i;
					}
					if (hull.get(i).x > hull.get(index_k).x) {
						index_k = i;
					}
					if (hull.get(i).y > hull.get(index_l).y) {
						index_l = i;
					}

				}

				index_i0 = index_i;
				index_j0 = index_j;
				index_k0 = index_k;
				index_l0 = index_l;

				// 2 : Create the lines of support.
				Line support_i, support_j, support_k, support_l;
				support_i = new Line(hull.get(index_i), new Vector(0, 1));
				support_j = new Line(hull.get(index_j), support_i.director.normal());
				support_k = new Line(hull.get(index_k), support_i.director.invert());
				support_l = new Line(hull.get(index_l), support_i.director.normal()
						.invert());

				// 3 : Get the angles.

				boolean hullScanned = false;
				boolean iStepped = false, jStepped = false, kStepped = false, lStepped = false;
				int count = 0;

				double theta_i, theta_j, theta_k, theta_l;
				double theta_min;
				int index_angle_min;
				Rectangle res = new Rectangle(support_i, support_j, support_k,
						support_l);
				double areaMax = 0;
				int i = 0;
				while (!hullScanned && i < 1) {

					theta_i = GeometryTools.cos(support_i, new Line(hull.get(index_i),
							hull.get((index_i + 1) % hull.size())));
					theta_j = GeometryTools.cos(support_j, new Line(hull.get(index_j),
							hull.get((index_j + 1) % hull.size())));
					if (theta_i > theta_j) {
						theta_min = theta_i;
						index_angle_min = index_i;
						System.out.println("i");
					} else {
						theta_min = theta_j;
						index_angle_min = index_j;
						System.out.println("j");
					}
					theta_k = GeometryTools.cos(support_k, new Line(hull.get(index_k),
							hull.get((index_k + 1) % hull.size())));
					if (theta_k > theta_min) {
						theta_min = theta_k;
						index_angle_min = index_k;
						System.out.println("k");
					}
					theta_l = GeometryTools.cos(support_l, new Line(hull.get(index_l),
							hull.get((index_l + 1) % hull.size())));
					if (theta_l > theta_min) {
						theta_min = theta_l;
						index_angle_min = index_l;
						System.out.println("l");
					}

					// 4 : The min point steps on.

					if (index_angle_min == index_i) {
						index_i = (index_i + 1) % hull.size();
						support_i.point = hull.get(index_i);

						support_i = new Line(hull.get(index_i), hull.get((index_i + 1)
								% hull.size()));
						support_j = new Line(hull.get(index_j),
								support_i.director.normal());
						support_k = new Line(hull.get(index_k),
								support_i.director.invert());
						support_l = new Line(hull.get(index_l), support_i.director
								.normal().invert());
						System.out.println("I");
						iStepped = true;
					} else if (index_angle_min == index_j) {
						index_j = (index_j + 1) % hull.size();
						support_j.point = hull.get(index_j);

						support_j = new Line(hull.get(index_j), hull.get((index_j + 1)
								% hull.size()));
						support_k = new Line(hull.get(index_k),
								support_j.director.normal());
						support_l = new Line(hull.get(index_l),
								support_j.director.invert());
						support_i = new Line(hull.get(index_i), support_j.director
								.normal().invert());
						System.out.println("J");
						jStepped = true;

					} else if (index_angle_min == index_k) {
						index_k = (index_k + 1) % hull.size();
						support_k.point = hull.get(index_k);

						support_k = new Line(hull.get(index_k), hull.get((index_k + 1)
								% hull.size()));
						support_l = new Line(hull.get(index_l),
								support_k.director.normal());
						support_i = new Line(hull.get(index_i),
								support_k.director.invert());
						support_j = new Line(hull.get(index_j), support_k.director
								.normal().invert());
						System.out.println("K");
						kStepped = true;

					} else {
						index_l = (index_l + 1) % hull.size();
						support_l.point = hull.get(index_l);

						support_l = new Line(hull.get(index_l), hull.get((index_l + 1)
								% hull.size()));
						support_i = new Line(hull.get(index_i),
								support_l.director.normal());
						support_j = new Line(hull.get(index_j),
								support_l.director.invert());
						support_k = new Line(hull.get(index_k), support_l.director
								.normal().invert());
						System.out.println("L");
						lStepped = true;

					}
					// 5 : Get rectangle's area.
					Rectangle rect = new Rectangle(support_i, support_j, support_k,
							support_l);
					
					rectangle = rect.getPoints();
					repaint();
					
					if (rect.area() > areaMax) {
						res = rect;
						areaMax = rect.area();

					}

					if (iStepped && index_i == index_i0 || jStepped
							&& index_j == index_j0 || kStepped && index_k == index_k0
							|| lStepped && index_l == index_l0) {
						count++;
					}
					hullScanned = (count >= 4);
				}
	
	}
}
