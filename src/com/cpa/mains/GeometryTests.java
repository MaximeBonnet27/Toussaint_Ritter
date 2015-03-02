package com.cpa.mains;

import javax.swing.SwingUtilities;

import com.cpa.view.TestsWindow;

public class GeometryTests {

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      
      @Override
      public void run() {
        TestsWindow tw = new TestsWindow();
        tw.setVisible(true);
      }
    });
  }
  
}
