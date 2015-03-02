package com.cpa.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.cpa.geometry.Line;
import com.cpa.geometry.Vertex;

@SuppressWarnings("serial")
public class TestsPanel extends JPanel {

  Line line;

  public TestsPanel() {
    line = new Line(new Vertex(500, 120), new Vertex(1, 1));
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
    drawLine(g, Color.WHITE);
  }

  private void drawPoint(Vertex p, Graphics g, Color color) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(color);
    g2.drawRect(p.x - 1, p.y - 1, 3, 3);
  }

  private void drawLine(Graphics g, Color color) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(color);
    g2.drawLine(line.point.x, line.point.y, line.point.x + 1000, (int) (line.point.y
        + 1000 * line.directionVectorY));
    g2.drawLine(line.point.x, line.point.y, line.point.x - 1000, (int) (line.point.y
        - 1000 * line.directionVectorY));
    drawPoint(line.point, g2, Color.RED);
    drawPoint(new Vertex(line.point.x + (int) line.directionVectorX + 100, line.point.y + (int) line.directionVectorY + 100 * (int) line.directionVectorY), g2, Color.GREEN);
  }

  public void rotateLine() {
    line.rotate(Math.PI / 6);
    repaint();
  }

}
