package com.cpa.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import com.cpa.geometry.GeometryTools;
import com.cpa.geometry.Vertex;

public class ConvexHull {

  public static ArrayList<Vertex> computeGrahamScan(ArrayList<Vertex> set) {

    ArrayList<Vertex> res = (ArrayList<Vertex>) set.clone();

    // Step 1 : Find p, a point inside of the Convex Hull
    Vertex x, y, z;
    x = res.get(0);
    y = res.get(1);
    int i = 2;
    Vertex p = null;
    boolean pFound = false;
    while (!pFound) {
      z = res.get(i);
      if (!GeometryTools.collinear(x, y, z)) {
        p = GeometryTools.centroid(x, y, z);
        pFound = true;
      }
      i++;
    }
    // Step 2 : Express each point in the set in polar coordinates
    // with origin p
    for (Vertex s : res) {
      s.initPolarCoordinates(p);
    }

    // Step 3 : Sort the set in terms of increasing theta
    Collections.sort(res);

    // Step 4 : Delete un-useful points
    for (i = 1; i < res.size(); i++) {
      if (res.get(i).theta == res.get(i - 1).theta) {
        res.remove(i);
        i--;
      } else if (res.get(i).squaredR == 0) {
        res.remove(i);
        i--;
      }
    }
    // Step 5 : Iteration over the set
    int k0 = 0, k1 = 1, k2 = 2;
    double alpha, beta;
    for(int iterations = 0; iterations < 2 * res.size(); iterations++){
      alpha = GeometryTools.angle(res.get(k0), res.get(k1), p);
      beta = GeometryTools.angle(p, res.get(k1), res.get(k2));
      // First case (i) : 
      if(alpha + beta >= Math.PI){
        res.remove(k1);
        k0 = (k0 - 1) % res.size();
        k1 = (k1 - 1) % res.size();
        
        // Maybe something to do with k2?
        // Done it at the end of the loop
      }
      // Second case (ii) : 
      else {
        k0 = (k0 + 1) % res.size();
        k1 = (k1 + 1) % res.size();
        k2 = (k2 + 1) % res.size();
      }

    }
    return res;
  }
  
  public static ArrayList<Vertex> graham(ArrayList<Vertex> points){
    ArrayList<Vertex> enveloppe = (ArrayList<Vertex>) points.clone();
    for(int i =0;i<enveloppe.size();i++){
      
      Vertex p = enveloppe.get(i);     
      Vertex q = enveloppe.get((i+1)%enveloppe.size());
      Vertex r = enveloppe.get((i+2)%enveloppe.size());
      if(!tourne_a_droite(p,q,r)){
        enveloppe.remove((i+1)%enveloppe.size());
        if(i != 0) { i--;}
        i--;
        
      }
    }
    
    return enveloppe;
  }

  public static boolean tourne_a_droite(Vertex p, Vertex q, Vertex r){
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
  public static ArrayList<Vertex> pixelSort2(ArrayList<Vertex> base){
    Vertex[] tabMax = new Vertex[2000];
    Vertex[] tabMin = new Vertex[2000];

    for(Vertex p : base){
      if(tabMax[(int) p.getX()] == null || tabMax[(int) p.getX()].getY() < p.getY()){
        tabMax[(int) p.getX()] = p;
      }
      if(tabMin[(int) p.getX()] == null || tabMin[(int) p.getX()].getY() > p.getY()){
        tabMin[(int) p.getX()] = p;
      }

    }

    ArrayList<Vertex> resultat = new ArrayList<>();
    for(Vertex p : tabMax){
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
