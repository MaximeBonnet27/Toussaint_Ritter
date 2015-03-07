package com.cpa.geometry;

import java.awt.Point;

@SuppressWarnings("serial")
public class Vertex extends Point.Double implements Comparable<Vertex>{
  
  /* Polar coordinates
   * Mainly needed for Graham Scan
   */
	
	
  // Angle coordinate
  public double theta;
  // Square of distance from the origin.
  public double squaredR;
  
  public Vertex(double d, double e) {
    super(d, e);
  }
  
  /**
   * Initializes the polar coordinates 
   * relative to the origin point.
   * @param origin
   */
  public void initPolarCoordinates(Point origin){
     squaredR = (x - origin.x)*(x - origin.x) 
         + (y - origin.y)*(y - origin.y);
     if(x == origin.x){
       theta = Math.PI / 2;
     }
     else {
       theta = Math.atan((double)(y - origin.y) / (x - origin.x));
     }
     
  }

  /** 
   * Compare two vertex,
   * requires the two vertex to be initialized
   * with polar coordinates
   */
  @Override
  public int compareTo(Vertex o) {
     if(theta < o.theta){
       return -1;
     }else if(theta == o.theta){
       return 0;
     }
     return 1;
  }

  @Override
  public String toString() {
    return "Vertex [theta=" + theta + ", squaredR=" + squaredR + ", x=" + x
        + ", y=" + y + "]";
  }
  
  
  
}
