package com.cpa.mains;

import com.cpa.algorithms.ConvexHull;
import com.cpa.algorithms.EnclosingRectangle;
import com.cpa.geometry.GeometryTools;
import com.cpa.geometry.Rectangle;
import com.cpa.geometry.Vertex;

import java.util.ArrayList;

import com.cpa.tools.TestFilesManager;

public class MainRandomListes {

	public static void main(String[] args) {
		ArrayList<Vertex> liste = null;
		ArrayList<Vertex> hull = null;
		Rectangle rect = null;
		int sum = 0;
		for(int i = 0; i < 10000; ++i){
			liste = TestFilesManager.getInstance().getRandomList();
			hull = ConvexHull.graham(ConvexHull.pixelSort2(liste));
			rect = EnclosingRectangle.computeToussaint(hull);
			if(rect.area() / GeometryTools.getArea(hull) < 1.0){
				System.out.println("DAMN MAN " + i );
				sum++;
			}
		}
		System.err.println(sum);
	}
	
}
