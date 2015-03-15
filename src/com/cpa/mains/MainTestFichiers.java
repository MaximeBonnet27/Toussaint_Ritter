package com.cpa.mains;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.cpa.algorithms.ConvexHull;
import com.cpa.algorithms.EnclosingRectangle;
import com.cpa.geometry.GeometryTools;
import com.cpa.geometry.Rectangle;
import com.cpa.geometry.Vertex;
import com.cpa.tools.TestFilesManager;

public class MainTestFichiers {

	
	public static void main(String[] args) throws IOException {
		
		String outputFileName = args[0];
		
		TestFilesManager tfm = TestFilesManager.getInstance();
		int i = 0;
		ArrayList<Vertex> set = null;
		ArrayList<Vertex> hull = null;
		Rectangle rect = null;
		double ratio = 0;
		double sum = 0;
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName));
		while((set = tfm.getNextFile()) != null){
			hull = ConvexHull.graham(ConvexHull.pixelSort2(set));
			rect = EnclosingRectangle.computeToussaint(hull);
			ratio = rect.area() / GeometryTools.getArea(hull);
			
			bw.write(tfm.getCurrentFileName());
			bw.write(";");
			bw.write(String.valueOf(rect.area()));
			bw.write(";");
			bw.write(String.valueOf(GeometryTools.getArea(hull)));
			bw.write(";");
			bw.write(String.valueOf(ratio));
			bw.write("\n");

			sum += ratio;
			i++;
		}
		System.out.println(sum / i);
		
		bw.close();
	}

	
}
