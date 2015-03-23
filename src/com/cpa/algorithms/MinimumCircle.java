package com.cpa.algorithms;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import com.cpa.geometry.Circle;

public class MinimumCircle {
  public static Circle computeRitter(ArrayList<Point.Double> points) {

    if (points.isEmpty())
      return null;

    // 1.
    Random random = new Random();
    Point.Double dummy = points.get(random.nextInt(points.size()));
    double distance = Integer.MIN_VALUE;
    // 2.
    Point.Double p = null;
    for (Point.Double temp : points) {
      double d = dummy.distance(temp);
      if (d > distance) {
        distance = d;
        p = temp;
      }
    }
    // 3.
    Point.Double q = null;
    for (Point.Double temp : points) {
      double d = p.distance(temp);
      if (d > distance) {
        distance = d;
        q = temp;
      }
    }

    Point.Double c, c2 = null;
    double cp = 0;

    // 4.
    double a = (p.x + q.x) / 2;
    double b = (p.y + q.y) / 2;

    c = new Point.Double(a, b);
    // 5.
    cp = Point.Double.distance(a, b, p.x, p.y);
    // 6.
    ArrayList<Point.Double> l = new ArrayList<Point.Double>();
    for (Point.Double temp : points) {
      if (Point.Double.distance(a, b, temp.x, temp.y) > cp) {
        l.add(temp);
      }
    }
    if (l.isEmpty()) {
      return new Circle(c, cp);
    }
    // 7.
    Point.Double s = null;

    while (!l.isEmpty()) {
      ArrayList<Point.Double> l1 = new ArrayList<Point.Double>();
      s = l.get(random.nextInt(l.size()));
      l.remove(s);
      // 8.

      double alpha, beta;
      double sc = Point.Double.distance(s.x, s.y, a, b);
      double st = sc + cp;
      double sc2 = st / 2;
      double cc2 = sc - sc2;
      alpha = sc2 / sc;
      beta = cc2 / sc;

      // 9.
      double a2 = alpha * a + beta * s.x;
      double b2 = alpha * b + beta * s.y;
      c2 = new Point.Double(a2, b2);
      for (Point.Double temp : l) {
        if (Point.Double.distance(a2, b2, temp.x, temp.y) > sc2) {
          l1.add(temp);
        }
      }
      if (l1.isEmpty()) {
        return new Circle(c2, sc2);
      } else {
        l = (ArrayList<Point.Double>) l1.clone();
        a = a2;
        b = b2;
        cp = sc2;
      }
      // 10.

    }
    return new Circle(c2, cp);

  }

}
