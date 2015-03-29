package com.cpa.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.cpa.algorithms.ConvexHull;
import com.cpa.geometry.GeometryTools;
import com.cpa.geometry.Line;
import com.cpa.geometry.Rectangle;
import com.cpa.geometry.Vector;
import com.cpa.tools.TestFilesManager;

public class AlgorithmCanvas extends JPanel{

	static ArrayList<Point.Double> set;
	static ArrayList<Point.Double> convexHull;
	public static ArrayList<Point.Double> rectangle;

	public AlgorithmCanvas() {
		this.set = new ArrayList<>();
		this.convexHull = new ArrayList<>();
		this.rectangle = new ArrayList<>();
	}

	public void nextTest() {
		init = false;
		TestFilesManager tfm = TestFilesManager.getInstance();
		set = tfm.getNextFile();//tfm.getRandomList();
		convexHull = new ArrayList<Point.Double>();
		rectangle = new ArrayList<Point.Double>();
		repaint();
	}

	public void generateConvexHull() {
		convexHull = ConvexHull.graham(ConvexHull.pixelSort(set));
		repaint();
	}
	static boolean init = false;
	public void generateRectangle() {
		if (convexHull.size() == 0)
			return;
		if(!init){
			initToussaint();
			init = true;;
		}
		computeToussaint();
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
		for (Point.Double v : set) {
			drawPoint(v, g2, Color.WHITE);
		}

		drawHull(g2, Color.RED);
		drawRectangle(g2, Color.GREEN);
	}

	private void drawPoint(Point.Double p, Graphics g, Color color) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
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

	static int index_i = 0;
	static int index_j = 0;
	static int index_k = 0;
	static int index_l = 0;
	static int index_i0, index_j0, index_k0, index_l0;
	static ArrayList<Point.Double> hull;
	static Line support_i, support_j, support_k, support_l;
	static boolean hullScanned = false;
	static boolean iStepped = false, jStepped = false, kStepped = false, lStepped = false;
	static int count = 0;
	static double cos_theta_i, cos_theta_j, cos_theta_k, cos_theta_l;
	static double cos_theta_max;
	static int index_cos_max;
	static Rectangle res, rect;
	static double areaMin;
	public static void initToussaint(){
		
		index_i = 0;
		index_j = 0;
		index_k = 0;
		index_l = 0;
		
		hull = convexHull;
		for (int i = 1; i < hull.size(); i++) {
			if (hull.get(i).x < hull.get(index_i).x) {
				index_i = i;
			}
			if (hull.get(i).y < hull.get(index_l).y) {
				index_l = i;
			}
			if (hull.get(i).x > hull.get(index_k).x) {
				index_k = i;
			}
			if (hull.get(i).y > hull.get(index_j).y) {
				index_j = i;
			}

		}

		index_i0 = index_i;
		index_j0 = index_j;
		index_k0 = index_k;
		index_l0 = index_l;

		support_i = new Line(hull.get(index_i), new Vector(0, 1));
		support_j = new Line(hull.get(index_j), new Vector(1,0));
		support_k = new Line(hull.get(index_k), new Vector(0,-1));
		support_l = new Line(hull.get(index_l), new Vector(-1,0));
		res = new Rectangle(support_i, support_j, support_k,
				support_l);
		areaMin = Double.MAX_VALUE;
		rect = res;


	}

	public static void computeToussaint(){
	  	  
		cos_theta_i = GeometryTools.cos(support_i, new Line(hull.get(index_i),
				hull.get((index_i + 1) % hull.size())));
		cos_theta_j = GeometryTools.cos(support_j, new Line(hull.get(index_j),
				hull.get((index_j + 1) % hull.size())));
		if (cos_theta_i > cos_theta_j) {
			cos_theta_max = cos_theta_i;
			index_cos_max = index_i;
		} else {
			cos_theta_max = cos_theta_j;
			index_cos_max = index_j;
		}
		cos_theta_k = GeometryTools.cos(support_k, new Line(hull.get(index_k),
				hull.get((index_k + 1) % hull.size())));
		if (cos_theta_k > cos_theta_max) {
			cos_theta_max = cos_theta_k;
			index_cos_max = index_k;
		}
		cos_theta_l = GeometryTools.cos(support_l, new Line(hull.get(index_l),
				hull.get((index_l + 1) % hull.size())));
		if (cos_theta_l > cos_theta_max) {
			cos_theta_max = cos_theta_l;
			index_cos_max = index_l;
		}
		// 4 : The min point steps on.
		if (index_cos_max == index_i) {
			support_i = new Line(hull.get(index_i), hull.get((index_i + 1)
					% hull.size()));
			support_j = new Line(hull.get(index_j),
					support_i.director.normal().invert());
			support_k = new Line(hull.get(index_k),
					support_i.director.invert());
			support_l = new Line(hull.get(index_l), support_j.director
					.invert());

			index_i = (index_i + 1) % hull.size();
			support_i.point = hull.get(index_i);

			iStepped = true;
		} else if (index_cos_max == index_j) {
			support_j = new Line(hull.get(index_j), hull.get((index_j + 1)
					% hull.size()));
			support_k = new Line(hull.get(index_k),
					support_j.director.normal().invert());
			support_l = new Line(hull.get(index_l),
					support_j.director.invert());
			support_i = new Line(hull.get(index_i), support_k.director
					.invert());

			index_j = (index_j + 1) % hull.size();
			support_j.point = hull.get(index_j);

			jStepped = true;

		} else if (index_cos_max == index_k) {
			support_k = new Line(hull.get(index_k), hull.get((index_k + 1)
					% hull.size()));
			support_l = new Line(hull.get(index_l),
					support_k.director.normal().invert());
			support_i = new Line(hull.get(index_i),
					support_k.director.invert());
			support_j = new Line(hull.get(index_j), support_l.director
					.invert());
			index_k = (index_k + 1) % hull.size();
			support_k.point = hull.get(index_k);
			kStepped = true;

		} else {

			support_l = new Line(hull.get(index_l), hull.get((index_l + 1)
					% hull.size()));
			support_i = new Line(hull.get(index_i),
					support_l.director.normal().invert());
			support_j = new Line(hull.get(index_j),
					support_l.director.invert());
			support_k = new Line(hull.get(index_k), support_i.director
					.invert());
			index_l = (index_l + 1) % hull.size();
			support_l.point = hull.get(index_l);
			lStepped = true;

		}

		// 5 : Get rectangle's area.
		rect = new Rectangle(support_i, support_j, support_k,
				support_l);

		if (rect.area() < areaMin) {
			res = rect;
			areaMin = rect.area();
		}

		if (iStepped && index_i == index_i0 || jStepped
				&& index_j == index_j0 || kStepped && index_k == index_k0
				|| lStepped && index_l == index_l0) {
			count++;
		}
		hullScanned = (count >= 4);
		rectangle = rect.getPoints();
	}	


}
