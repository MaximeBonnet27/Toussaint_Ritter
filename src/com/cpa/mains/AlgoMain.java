package com.cpa.mains;

import javax.swing.SwingUtilities;

import com.cpa.view.AlgorithmViewer;

/**
 * Main permettant de suivre l'évolution du rectangle pendant 
 * l'algorithme Toussaint.
 * Commandes : 
 * N -> Fichier Suivant
 * H -> Enveloppe Convexe
 * R -> (require H) Prochain rectangle (rappuyer pour avancer dans l'algo).
 * @author Maxime Bonnet
 *
 */
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
