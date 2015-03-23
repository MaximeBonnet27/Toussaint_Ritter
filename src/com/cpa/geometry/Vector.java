package com.cpa.geometry;

/**
 * Un vecteur, représenté par ses coordonnées cartésiennes.
 * 
 * @author Maxime Bonnet
 * 
 */
public class Vector {
	/**
	 * Les coordonnées cartésiennes du vecteur.
	 */
	public double x, y;

	public Vector(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	/**
	 * Renvoie le coefficient directeur entre ce vecteur et le vecteur passé en
	 * paramètre.
	 * 
	 * @param other
	 *            Un autre vecteur
	 * @return le coefficient directeur entre les deux vecteurs
	 */
	public double dotProduct(Vector other) {
		return x * other.x + y * other.y;
	}

	/**
	 * Renvoie un vecteur normal à ce vecteur (rotation - PI / 2)
	 * 
	 * @return un vecteur normal à ce vecteur
	 */
	public Vector normal() {
		return new Vector(-y, x);
	}

	/**
	 * Renvoie un vecteur opposé à ce vecteur (rotation PI)
	 * 
	 * @return un vecteur opposé à ce vecteur
	 */
	public Vector invert() {
		return normal().normal();
	}

	/**
	 * Renvoie la norme de ce vecteur
	 * 
	 * @return la norme de ce vecteur
	 */
	public double magnitude() {
		return Math.sqrt(x * x + y * y);
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}

}
