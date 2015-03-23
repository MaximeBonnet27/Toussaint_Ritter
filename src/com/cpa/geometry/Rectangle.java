package com.cpa.geometry;

import java.awt.Point;
import java.util.ArrayList;

public class Rectangle {

	public Point.Double a, b, c, d;

	public Rectangle(Line l1, Line l2, Line l3, Line l4) {
	  this(l1.intersection(l2), l2.intersection(l3), l3.intersection(l4), l4.intersection(l1));
  }

	public Rectangle(Point.Double a, Point.Double b, Point.Double c, Point.Double d) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}

	public double area() {
		return b.distance(a) * b.distance(c);
	}

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
