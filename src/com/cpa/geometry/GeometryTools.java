package com.cpa.geometry;

import java.util.ArrayList;

public class GeometryTools {

	public static double cos(Line l1, Line l2) {
		double cos =  l1.director.dotProduct(l2.director)
				/ (l1.director.magnitude() * l2.director.magnitude());
		//return cos;
		// BLACK MAGIC
		return Math.abs(cos);

	}
	
	public static double getArea(ArrayList<Vertex> hull) {
		double firstSum = 0;
		for(int i = 0; i < hull.size(); i++){
			firstSum += hull.get(i).x * hull.get((i + 1) % hull.size()).y;
		}
		double secondSum = 0;
		for(int i = 0; i < hull.size(); i++){
			secondSum += hull.get(i).y * hull.get((i + 1) % hull.size()).x;
		}
		return 0.5 * Math.abs((firstSum - secondSum));
	}

}
