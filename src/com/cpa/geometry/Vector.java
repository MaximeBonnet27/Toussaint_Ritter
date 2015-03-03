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
		return new Vector(-y, x);
	}
	
	public Vector invert(){
		return new Vector(-x, y);
	}
	
	public double magnitude(){
		return Math.sqrt(x * x + y * y);
	}
	
}
