package com.cpa.geometry;

public class GeometryTools {

	public static boolean collinear(Vertex x, Vertex y, Vertex z) {
		return (x.x * (y.y - z.y) + (y.x * (z.y - x.y) + (z.x * (x.y - y.y))) == 0);
	}

	public static Vertex centroid(Vertex x, Vertex y, Vertex z) {
		return new Vertex((x.x + y.x + z.x) / 3, (x.y + y.y + z.y) / 3);
	}

	public static double angle(Vertex x, Vertex y, Vertex z) {

		double a = y.distance(z);
		double b = x.distance(y);
		double c = x.distance(z);
		if (a == 0 || b == 0) {
			return 0;
		} else {
			return (a * a + b * b - c * c) / (2 * a * b);
		}

	}

	public static double cos(Line l1, Line l2) {
		double cos =  l1.director.dotProduct(l2.director)
				/ (l1.director.magnitude() * l2.director.magnitude());
		return cos;

	}

	/*
	 * public static double angle(Line l1, Line l2){ return Math.atan((l2.a -
	 * l1.a) / (1 + l2.a * l1.a)); }
	 * 
	 * public static Vertex intersection(Line l1, Line l2) { int x,y; if(l2.b ==
	 * l1.b) x = 0; else x = (int) ((l1.a - l2.a) / (l2.b - l1.b)); y = (int)
	 * (l1.a * x + l1.b); return new Vertex(x, y); }
	 */

}
