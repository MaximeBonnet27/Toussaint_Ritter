package com.cpa.geometry;

public class Vector {

	public double x, y;

	public Vector(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public double dotProduct(Vector other){
		return x * other.x + y * other.y;
	}
	
	public Vector normal(){
	  Vector res = new Vector(-y, x);
//	  System.out.println(this + " -> " + res);
		return res;
	}
	
	public Vector invert(){
		return normal().normal();
	}
	
	public double magnitude(){
		return Math.sqrt(x * x + y * y);
	}
	
	@Override
	public String toString() {
	  return "("+x+","+y+")";
	}
	
}
