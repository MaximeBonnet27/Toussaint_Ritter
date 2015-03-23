package com.cpa.geometry;

public class Line {

	public Vector director;
	public Vertex point;

	public Line(Vertex v, Vertex w) {
		if (w.x == v.x) {
			director = new Vector(0.0, 1.0);
		} else {
			director = new Vector(w.x - v.x, (w.y - v.y));

		}
		point = v;
	}

	public Line(Vertex point, Vector director) {
		this.point = point;
		this.director = director;
	}

	public Vertex intersection(Line other) {
		double xInter, yInter;
		if(director.x == 0){
			if(other.director.x == 0){
				return null;
			}
			return new Vertex(point.x, (other.getSlope() * point.x + other.getB()));
		}
		if(other.director.x == 0){
			return new Vertex(other.point.x, (getSlope() * other.point.x + getB()));
		}
		if(other.getSlope() == getSlope()) return null;

		xInter = (other.getB() - getB()) / (getSlope() - other.getSlope());
		yInter = getSlope() * xInter + getB();
		return new Vertex (xInter, yInter);

	}


	@Override
	public String toString() {
		return "Line [directionVectorX=" + director.x + ", directionVectorY="
				+ director.y + ", point=" + point + "]";
	}

	public double getSlope(){
		return director.y / director.x;
	}

	public double getB(){
		return point.y - getSlope() * point.x;
	}

}
