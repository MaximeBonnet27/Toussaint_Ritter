package com.cpa.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainWindow extends JFrame implements KeyListener{

  private CanvasPanel canvas;
  
  public MainWindow() {
    initUI();
  }
  
  private void initUI(){
    
    createCanvas();
    
    setTitle("CPA_VIEWER");
    setSize(800,600);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    addKeyListener(this);

  }
  
  private void createCanvas(){
    canvas = new CanvasPanel();
    add(canvas);
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if(e.getKeyCode() == KeyEvent.VK_N){
      canvas.nextTest();
    }
    if(e.getKeyCode() == KeyEvent.VK_H){
      canvas.generateConvexHull();
    }
    if(e.getKeyCode() == KeyEvent.VK_R){
      canvas.generateRectangle();
    }
    if(e.getKeyCode() == KeyEvent.VK_C){
      canvas.generateCircle();
    }
    if(e.getKeyCode() == KeyEvent.VK_PAGE_UP){
      canvas.zoomIn();
    }
    if(e.getKeyCode() == KeyEvent.VK_PAGE_DOWN){
      canvas.zoomOut();
    }
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
