package com.cpa.mains;

import java.awt.Point;
import java.util.ArrayList;

import com.cpa.algorithms.ConvexHull;
import com.cpa.algorithms.EnclosingRectangle;
import com.cpa.geometry.GeometryTools;
import com.cpa.geometry.Rectangle;
import com.cpa.tools.TestFilesManager;

/**
 * Un main testant l'algorithme Toussaint sur des points aléatoires.
 * @author Maxime Bonnet
 *
 */
public class MainRandomListes {

	public static void main(String[] args) {
		ArrayList<Point.Double> liste = null;
		ArrayList<Point.Double> hull = null;
		Rectangle rect = null;
		int sum = 0;
		for(int i = 0; i < 100000; ++i){
			liste = TestFilesManager.getInstance().getRandomList();
			hull = ConvexHull.graham(ConvexHull.pixelSort(liste));
			try{
			rect = EnclosingRectangle.computeToussaint(hull);
			}catch(IllegalArgumentException e){
			  i--; continue;
			}
			if(rect.area() / GeometryTools.getArea(hull) < 1.0){
				sum++;
			}
		}
		System.out.println("Nombre d'erreurs : " + sum);
	}
	
}
