package com.cpa.geometry;


public class Line {

  public double directionVectorX, directionVectorY;
  public Vertex point;

  public Line(Vertex v, Vertex w){
    if(w.x == v.x){
      directionVectorX = 0.0;
      directionVectorY = 1.0;
    }
    else{
      directionVectorX = 1.0;
      directionVectorY = ((double) w.y - v.y) / (w.x - v.x);
      System.out.println(w.y +" "+ v.y + " " +directionVectorY);
      System.out.println(w.x +" "+ v.x + " " +directionVectorX);

    }
    point = v;
    System.out.println(this);
  }

  public void rotate(double theta) {
    double newX = directionVectorX * Math.cos(theta) - directionVectorY * Math.sin(theta);
    double newY = directionVectorX * Math.sin(theta) + directionVectorY * Math.cos(theta);
    directionVectorX = newX;
    directionVectorY = newY;
    System.out.println("(" + directionVectorX +"," + directionVectorY +")");
  }

  @Override
  public String toString() {
    return "Line [directionVectorX=" + directionVectorX + ", directionVectorY="
        + directionVectorY + ", point=" + point + "]";
  }

  
}
