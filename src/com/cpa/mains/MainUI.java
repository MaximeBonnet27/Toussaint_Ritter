package com.cpa.mains;

import javax.swing.SwingUtilities;

import com.cpa.view.MainWindow;

/**
 * Main 
 * Commandes : 
 * N -> Fichier Suivant
 * H -> Enveloppe Convexe
 * C -> Cercle minimum 
 * R -> (require H) Prochain rectangle (rappuyer pour avancer dans l'algo).
 * @author Maxime Bonnet
 *
 */
public class MainUI {

  public static void main(String[] args) {
    
    
    SwingUtilities.invokeLater(new Runnable() {

      @Override
      public void run(){
        MainWindow mw = new MainWindow();
        mw.setVisible(true);
      }
    }
        );
  }

}
