package com.cpa.geometry;

import java.awt.Point;

/**
 * Classe représentant un cercle, utilisée dans le calcul du cercle minimum
 * grâce à l'algorithme de Ritter.
 * 
 * @author Maxime Bonnet
 * 
 */
public class Circle {
	/**
	 * Centre du cercle
	 */
	public Point.Double center;
	/**
	 * Rayon du cercle
	 */
	public double radius;

	/**
	 * @param center
	 *            Le centre du cercle
	 * @param radius
	 *            Le rayon du cercle
	 */
	public Circle(Point.Double center, double radius) {
		super();
		this.center = center;
		this.radius = radius;
	}

}
