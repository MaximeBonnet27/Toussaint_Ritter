package com.cpa.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class TestsWindow extends JFrame implements KeyListener{

  private TestsPanel canvas;
  
  public TestsWindow() {
    initUI();
  }
  
  private void initUI(){
    
    createCanvas();
    
    setTitle("TESTS");
    setSize(800,600);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    addKeyListener(this);

  }
  
  private void createCanvas(){
    canvas = new TestsPanel();
    add(canvas);
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if(e.getKeyCode() == KeyEvent.VK_Q){
      System.exit(0);
    }
  }

  @Override
  public void keyReleased(KeyEvent arg0) {
  }

  @Override
  public void keyTyped(KeyEvent arg0) {
  }
  

}
