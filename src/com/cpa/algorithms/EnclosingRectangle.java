package com.cpa.algorithms;

import java.util.ArrayList;

import com.cpa.geometry.GeometryTools;
import com.cpa.geometry.Line;
import com.cpa.geometry.Rectangle;
import com.cpa.geometry.Vertex;

public class EnclosingRectangle {

  public static ArrayList<Vertex> computeToussaint(ArrayList<Vertex> hull) {

    // 1: Find the 4 extreme points.

    int index_i = 0, index_j = 0, index_k = 0, index_l = 0;
    int index_i0;
    for (int i = 1; i < hull.size(); i++) {
      if (hull.get(i).x < hull.get(index_i).x) {
        index_i = i;
      }
      if (hull.get(i).y < hull.get(index_j).y) {
        index_j = i;
      }
      if (hull.get(i).x > hull.get(index_k).x) {
        index_k = i;
      }
      if (hull.get(i).y > hull.get(index_l).y) {
        index_l = i;
      }

    }
    
    
    index_i0 = index_i;

    // 2 : Create the lines of support.
    Line support_i, support_j, support_k, support_l;
    support_i = new Line(hull.get(index_i), new Vertex(hull.get(index_i).x, hull.get(index_i).y + 1));
    support_j = new Line(hull.get(index_j), new Vertex(hull.get(index_j).x + 1, hull.get(index_j).y));
    support_k = new Line(hull.get(index_k), new Vertex(hull.get(index_k).x, hull.get(index_k).y + 1));
    support_l = new Line(hull.get(index_l), new Vertex(hull.get(index_l).x + 1, hull.get(index_l).y));

    System.out.println(support_i);
    System.out.println(support_j);
    // 3 : Get the angles.

    boolean hullScanned = false;
    boolean iStepped = false;

    double theta_i, theta_j, theta_k, theta_l;
    double theta_min;
    int index_angle_min;
    Rectangle res = null;
    double areaMax = 0;

    while (!hullScanned) {
      
      
      
      theta_i = GeometryTools.angle(support_i,
          new Line(hull.get(index_i), hull.get((index_i + 1) % hull.size())));
      theta_j = GeometryTools.angle(support_j,
          new Line(hull.get(index_j), hull.get((index_j + 1) % hull.size())));
      if (Math.abs(theta_i) < Math.abs(theta_j)) {
        theta_min = theta_i;
        index_angle_min = index_i;
      } else {
        theta_min = theta_j;
        index_angle_min = index_j;
      }
      theta_k = GeometryTools.angle(support_k,
          new Line(hull.get(index_k), hull.get((index_k + 1) % hull.size())));
      if (Math.abs(theta_k) < Math.abs(theta_min)) {
        theta_min = theta_k;
        index_angle_min = index_k;
      }
      theta_l = GeometryTools.angle(support_l,
          new Line(hull.get(index_l), hull.get((index_l + 1) % hull.size())));
      if (Math.abs(theta_l) < Math.abs(theta_min)) {
        theta_min = theta_l;
        index_angle_min = index_l;
      }


      // 4 : The min point steps on.

      if (index_angle_min == index_i) {
        index_i = (index_i + 1) % hull.size();
        support_i.point = hull.get(index_i);
        iStepped = true;
      } else if (index_angle_min == index_j) {
        index_j = (index_j + 1) % hull.size();
        support_j.point = hull.get(index_j);

      } else if (index_angle_min == index_k) {
        index_k = (index_k + 1) % hull.size();
        support_k.point = hull.get(index_k);

      } else {
        index_l = (index_l + 1) % hull.size();
        support_l.point = hull.get(index_l);

      }
      // 5 : Get rectangle's area.
      Rectangle rect = new Rectangle(support_i, support_j, support_k, support_l);
      if (rect.area() > areaMax) {
        res = rect;
        areaMax = rect.area();

      }
      support_i.rotate(theta_min);
      support_j.rotate(theta_min);
      support_k.rotate(theta_min);
      support_l.rotate(theta_min);

      hullScanned = iStepped && (index_i == index_i0);
    }
    return res.getPoints();
  }


}
