package com.cpa.mains;

import javax.swing.SwingUtilities;

import com.cpa.view.AlgorithmViewer;
import com.cpa.view.TestsWindow;

public class AlgoMain {


	  public static void main(String[] args) {
	    SwingUtilities.invokeLater(new Runnable() {
	      
	      @Override
	      public void run() {
	        AlgorithmViewer tw = new AlgorithmViewer();
	        tw.setVisible(true);
	      }
	    });
	  }	
}
