package com.cpa.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.cpa.geometry.Line;
import com.cpa.geometry.Vector;
import com.cpa.geometry.Vertex;

@SuppressWarnings("serial")
public class TestsPanel extends JPanel {

  Line l;
  Line l2;

  public TestsPanel() {
    l = new Line(new Vertex(500, 120), new Vertex(1, 1));
    l2 = new Line(new Vertex(400,80), new Vector(1, 0.654));
    requestFocus();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    draw(g);
    setBackground(Color.BLACK);
  }

  private void draw(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    drawLine(l,g, Color.WHITE);
    drawLine(l2, g2, Color.WHITE);
  }

  private void drawPoint(Vertex p, Graphics g, Color color) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(color);
    g2.drawRect((int) p.x - 1, (int) p.y - 1, 3, 3);
  }

  private void drawLine(Line line, Graphics g, Color color) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(color);
    g2.drawLine((int)line.point.x,(int) line.point.y, (int)line.point.x + 1000, (int) (line.point.y
        + 1000 * line.director.y));
    g2.drawLine((int)line.point.x, (int)line.point.y,(int) line.point.x - 1000, (int) (line.point.y
        - 1000 * line.director.y));
    drawPoint(line.point, g2, Color.RED);
    drawPoint(new Vertex(line.point.x + (int) line.director.x + 100, line.point.y + (int) line.director.y + 100 * (int) line.director.y), g2, Color.GREEN);
  }

  public void rotateLine() {
    repaint();
  }

}
