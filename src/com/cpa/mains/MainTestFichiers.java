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
		double ratioRect = 0, ratioCircle = 0;
		double sum = 0;
		double sumCircle = 0;
		double rectArea, hullArea, circleArea;
		BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName));
		while ((set = tfm.getNextFile()) != null) {
			hull = ConvexHull.graham(ConvexHull.pixelSort(set));
			rect = EnclosingRectangle.computeToussaint(hull);
			c = MinimumCircle.computeRitter(set);
			circleArea = c.area();
			rectArea = rect.area();
			hullArea = GeometryTools.getArea(hull);
			ratioRect = rectArea / hullArea;
			ratioCircle = circleArea / hullArea;
			bw.write(tfm.getCurrentFileName());
			bw.write(",");
			bw.write(String.valueOf(rectArea));
			bw.write(",");
			bw.write(String.valueOf(hullArea));
			bw.write(",");
			bw.write(String.valueOf(ratioRect));
			bw.write(",");
			bw.write(String.valueOf(ratioCircle));
			bw.write("\n");
			System.out.println(ratioRect);
			sumCircle += ratioCircle;
			sum += ratioRect;
			i++;
		}
		System.out.println("RECT : " + sum / i);
		System.out.println("CIRCLE : " + sumCircle / i);
		bw.close();
	}

}
