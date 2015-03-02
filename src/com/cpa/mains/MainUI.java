package com.cpa.mains;

import javax.swing.SwingUtilities;

import com.cpa.view.MainWindow;

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
