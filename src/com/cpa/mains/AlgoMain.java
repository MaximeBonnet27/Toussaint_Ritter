package com.cpa.mains;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import com.cpa.algorithms.ConvexHull;
import com.cpa.algorithms.EnclosingRectangle;
import com.cpa.geometry.Vertex;
import com.cpa.tools.TestFilesManager;
import com.cpa.view.AlgorithmViewer;

public class AlgoMain {


	  public static void main(String[] args) {
	    SwingUtilities.invokeLater(new Runnable() {
	      
	      @Override
	      public void run() {
	        AlgorithmViewer tw = new AlgorithmViewer();
	        tw.setVisible(true);
	      }
	    });
		TestFilesManager tfm = TestFilesManager.getInstance();
		tfm.getNextFile();
		ArrayList<Vertex> set = tfm.getNextFile();
		EnclosingRectangle.computeToussaint(ConvexHull.graham(set));
	  }	
}
