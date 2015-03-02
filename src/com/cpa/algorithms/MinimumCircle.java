package com.cpa.algorithms;

import java.util.ArrayList;
import java.util.Random;

import com.cpa.geometry.Circle;
import com.cpa.geometry.Vertex;

public class MinimumCircle {
  public static Circle computeRitter(ArrayList<Vertex> points) {

    if (points.isEmpty())
      return null;

    // 1.
    Random random = new Random();
    Vertex dummy = points.get(random.nextInt(points.size()));
    double distance = Integer.MIN_VALUE;
    // 2.
    Vertex p = null;
    for (Vertex temp : points) {
      double d = dummy.distance(temp);
      if (d > distance) {
        distance = d;
        p = temp;
      }
    }
    // 3.
    Vertex q = null;
    for (Vertex temp : points) {
      double d = p.distance(temp);
      if (d > distance) {
        distance = d;
        q = temp;
      }
    }

    Vertex c, c2 = null;
    double cp = 0;

    // 4.
    double a = (p.x + q.x) / 2;
    double b = (p.y + q.y) / 2;

    c = new Vertex((int) a, (int) b);
    // 5.
    cp = Vertex.distance(a, b, p.x, p.y);
    // 6.
    ArrayList<Vertex> l = new ArrayList<Vertex>();
    for (Vertex temp : points) {
      if (Vertex.distance(a, b, temp.x, temp.y) > cp) {
        l.add(temp);
      }
    }
    if (l.isEmpty()) {
      return new Circle(c, cp);
    }
    // 7.
    Vertex s = null;

    while (!l.isEmpty()) {
      ArrayList<Vertex> l1 = new ArrayList<Vertex>();
      s = l.get(random.nextInt(l.size()));
      l.remove(s);
      // 8.

      double alpha, beta;
      double sc = Vertex.distance(s.x, s.y, a, b);
      double st = sc + cp;
      double sc2 = st / 2;
      double cc2 = sc - sc2;
      alpha = sc2 / sc;
      beta = cc2 / sc;

      // 9.
      double a2 = alpha * a + beta * s.x;
      double b2 = alpha * b + beta * s.y;
      c2 = new Vertex((int) a2, (int) b2);
      for (Vertex temp : l) {
        if (Vertex.distance(a2, b2, temp.x, temp.y) > sc2) {
          l1.add(temp);
        }
      }
      if (l1.isEmpty()) {
        return new Circle(c2, (int) sc2);
      } else {
        l = (ArrayList<Vertex>) l1.clone();
        a = a2;
        b = b2;
        cp = sc2;
      }
      // 10.

    }
    return new Circle(c2, cp);

  }

}
