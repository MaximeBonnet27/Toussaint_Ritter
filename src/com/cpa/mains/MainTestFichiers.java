package com.cpa.mains;

import java.awt.Point;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.cpa.algorithms.ConvexHull;
import com.cpa.algorithms.EnclosingRectangle;
import com.cpa.algorithms.MinimumCircle;
import com.cpa.geometry.Circle;
import com.cpa.geometry.GeometryTools;
import com.cpa.geometry.Rectangle;
import com.cpa.tools.TestFilesManager;

/**
 * Test créant un fichier csv a partir des résultats obtenus sur la base de
 * tests Varoumas
 * 
 * @author Maxime Bonnet
 * 
 */
public class MainTestFichiers {

	public static void main(String[] args) throws IOException {

		String outputFileName = args[0];

		TestFilesManager tfm = TestFilesManager.getInstance();
		int i = 0;
		ArrayList<Point.Double> set = null;
		ArrayList<Point.Double> hull = null;
		Rectangle rect = null;
		Circle c = null;
		double ratio = 0;
		double sum = 0;
		double rectArea, hullArea, circleArea;
		double sumRect = 0;
		double sumHull = 0; 
		double sumCircle = 0;
		BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName));
		while ((set = tfm.getNextFile()) != null) {
			hull = ConvexHull.graham(ConvexHull.pixelSort(set));
			rect = EnclosingRectangle.computeToussaint(hull);
			c = MinimumCircle.computeRitter(set);
			circleArea = c.area();
			rectArea = rect.area();
			hullArea = GeometryTools.getArea(hull);
			ratio = rectArea / hullArea;
			
			bw.write(tfm.getCurrentFileName());
			bw.write(",");
			bw.write(String.valueOf(rectArea));
			bw.write(",");
			bw.write(String.valueOf(hullArea));
			bw.write(",");
			bw.write(String.valueOf(ratio));
			bw.write("\n");
			System.out.println(ratio);
			sumHull += hullArea;
			sumRect += rectArea;
			sumCircle += circleArea;
			sum += ratio;
			i++;
		}
		System.out.println(sum / i);

		bw.close();
	}

}
