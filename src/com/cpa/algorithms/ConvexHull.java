package com.cpa.algorithms;

import java.awt.Point;
import java.util.ArrayList;

public class ConvexHull {


  public static ArrayList<Point.Double> graham(ArrayList<Point.Double> points){
    ArrayList<Point.Double> enveloppe = (ArrayList<Point.Double>) points.clone();
    for(int i =0;i<enveloppe.size();i++){
      
      Point.Double p = enveloppe.get(i);     
      Point.Double q = enveloppe.get((i+1)%enveloppe.size());
      Point.Double r = enveloppe.get((i+2)%enveloppe.size());
      if(!tourne_a_droite(p,q,r)){
        enveloppe.remove((i+1)%enveloppe.size());
        if(i != 0) { i--;}
        i--;
        
      }
    }
    
    return enveloppe;
  }

  public static boolean tourne_a_droite(Point.Double p, Point.Double q, Point.Double r){
    double pqX = q.getX()-p.getX();
    double pqY = q.getY()-p.getY();
    double qrX = r.getX()-p.getX();
    double qrY = r.getY()-p.getY();
    double prodV = produitVectoriel(pqX, pqY, qrX, qrY);
    if(prodV <= 0){
      return true;
    }
    else {
      return false;
    }
  }
  public static double produitVectoriel(double x1, double y1, double x2, double y2) {
    double res = x1 * y2 - x2 * y1;
    return res;
  }
  public static ArrayList<Point.Double> pixelSort2(ArrayList<Point.Double> base){
    Point.Double[] tabMax = new Point.Double[2000];
    Point.Double[] tabMin = new Point.Double[2000];

    for(Point.Double p : base){
      if(tabMax[(int) p.getX()] == null || tabMax[(int) p.getX()].getY() < p.getY()){
        tabMax[(int) p.getX()] = p;
      }
      if(tabMin[(int) p.getX()] == null || tabMin[(int) p.getX()].getY() > p.getY()){
        tabMin[(int) p.getX()] = p;
      }

    }

    ArrayList<Point.Double> resultat = new ArrayList<>();
    for(Point.Double p : tabMax){
      if(p != null){
        resultat.add(p);
      }
    }
    int first;
    for(first=tabMin.length - 1;tabMin[first]==null;first--);
    if (tabMin[first].equals(resultat.get(resultat.size()-1))) first--;
    
    for(int i = first; i >= 0; --i){
      if(null != tabMin[i]){
        resultat.add(tabMin[i]);
      }
    }
    
    if(resultat.get(resultat.size()-1).equals(resultat.get(0))) resultat.remove(0);
  

    return resultat;

  }


  
}
