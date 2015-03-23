package com.cpa.geometry;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Un rectangle, représenté par 4 points. 
 * @author 3100381
 * 
 */
public class Rectangle {
	/**
	 * Les quatres coins du rectangle.
	 */
	public Point.Double a, b, c, d;

	/* Constructeurs */
	public Rectangle(Line l1, Line l2, Line l3, Line l4) {
		this(l1.intersection(l2), l2.intersection(l3), l3.intersection(l4), l4
				.intersection(l1));
	}

	public Rectangle(Point.Double a, Point.Double b, Point.Double c,
			Point.Double d) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}
	/**
	 * Renvoie l'aire du rectangle
	 * @return l'aire du rectangle
	 */
	public double area() {
		return b.distance(a) * b.distance(c);
	}
	/**
	 * Renvoie une liste composée des 4 coins du rectangle.
	 * @return une liste composée des 4 coins du rectangle
	 */
	public ArrayList<Point.Double> getPoints() {
		ArrayList<Point.Double> result = new ArrayList<Point.Double>();
		result.add(a);
		result.add(b);
		result.add(c);
		result.add(d);
		return result;
	}

	@Override
	public String toString() {
		return "Rectangle [a=" + a + ", b=" + b + ", c=" + c + ", d=" + d + "]";
	}

}
