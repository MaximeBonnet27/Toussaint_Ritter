package com.cpa.geometry;

public class Line {

	public Vector director;
	public Vertex point;

	public Line(Vertex v, Vertex w) {
		if (w.x == v.x) {
			director = new Vector(0.0,1.0);
		} else {
			director = new Vector(1.0,((double) w.y - v.y) / (w.x - v.x));

		}
		point = v;
	}

	public Line(Vertex point, Vector director) {
		this.point = point;
		this.director = director;
	}

	public Vertex intersection(Line other) {
		double x1 = point.x;
		double y1 = point.y;
		double x2 = point.x + director.x;
		double y2 = point.y + director.y;
		double x3 = other.point.x;
		double y3 = other.point.y;
		double x4 = other.point.x + other.director.x;
		double y4 = other.point.y + other.director.y;
		
		double x12 = x1 - x2;
		double x34 = x3 - x4;
		double y12 = y1 - y2;
		double y34 = y3 - y4;

		double c = x12 * y34 - y12 * x34;

		if (Math.abs(c) <= 0.0001)
		{
		  return null;
		}
		else
		{
		  // Intersection
		  double a = x1 * y2 - y1 * x2;
		  double b = x3 * y4 - y3 * x4;

		  double x = (a * x34 - b * x12) / c;
		  double y = (a * y34 - b * y12) / c;

		  return new Vertex((int) x,(int)y);
		}
		
	}

	@Override
	public String toString() {
		return "Line [directionVectorX=" + director.x + ", directionVectorY="
				+ director.y + ", point=" + point + "]";
	}

}
