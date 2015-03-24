package com.cpa.mains;

import java.awt.Point;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.cpa.algorithms.ConvexHull;
import com.cpa.algorithms.EnclosingRectangle;
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
		double ratio = 0;
		double sum = 0;

		BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName));
		while ((set = tfm.getNextFile()) != null) {
			hull = ConvexHull.graham(ConvexHull.pixelSort(set));
			rect = EnclosingRectangle.computeToussaint(hull);
			ratio = rect.area() / GeometryTools.getArea(hull);

			bw.write(tfm.getCurrentFileName());
			bw.write(",");
			bw.write(String.valueOf(rect.area()));
			bw.write(",");
			bw.write(String.valueOf(GeometryTools.getArea(hull)));
			bw.write(",");
			bw.write(String.valueOf(ratio));
			bw.write("\n");

			sum += ratio;
			i++;
		}
		System.out.println(sum / i);

		bw.close();
	}

}
