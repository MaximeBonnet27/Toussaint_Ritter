package com.cpa.geometry;

/**
 * Un vecteur, repr�sent� par ses coordonn�es cart�siennes.
 * 
 * @author Maxime Bonnet
 * 
 */
public class Vector {
	/**
	 * Les coordonn�es cart�siennes du vecteur.
	 */
	public double x, y;

	public Vector(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	/**
	 * Renvoie le coefficient directeur entre ce vecteur et le vecteur pass� en
	 * param�tre.
	 * 
	 * @param other
	 *            Un autre vecteur
	 * @return le coefficient directeur entre les deux vecteurs
	 */
	public double dotProduct(Vector other) {
		return x * other.x + y * other.y;
	}

	/**
	 * Renvoie un vecteur normal � ce vecteur (rotation - PI / 2)
	 * 
	 * @return un vecteur normal � ce vecteur
	 */
	public Vector normal() {
		return new Vector(-y, x);
	}

	/**
	 * Renvoie un vecteur oppos� � ce vecteur (rotation PI)
	 * 
	 * @return un vecteur oppos� � ce vecteur
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
