package com.cpa.geometry;

import java.awt.Point;

/**
 * Une droite, représentée par un point et un vecteur directeur.
 * 
 * @author Maxime Bonnet
 * 
 */
public class Line {
	/**
	 * Vecteur directeur
	 */
	public Vector director;
	/**
	 * Point de base de la droite
	 */
	public Point.Double point;

	/* Constructeurs */

	public Line(Point.Double v, Point.Double w) {
		if (w.x == v.x) {
			director = new Vector(0.0, 1.0);
		} else {
			director = new Vector((w.x - v.x), (w.y - v.y));
		}
		point = v;
	}

	public Line(Point.Double point, Vector director) {
		this.point = point;
		this.director = director;
	}

	/**
	 * Retourne le point d'intersection entre cette droite et l'autre droite,
	 * passée en paramètre.
	 * 
	 * @param other
	 *            L'autre droite.
	 * @return le point d'intersection entre les deux droites.
	 */
	public Point.Double intersection(Line other) {
		double xInter, yInter;
		// Si la droite actuelle (this) est verticale
		if (director.x == 0) {
			// Si l'autre l'est également.
			if (other.director.x == 0) {
				// Alors pas d'intersection
				return null;
			}
			// Sinon, le point de l'autre droite à l'abscisse de notre droite.
			return new Point.Double(point.x,
					(other.getSlope() * point.x + other.getB()));
		}
		// Si l'autre droite est verticale.
		if (other.director.x == 0) {
			// On renvoie le point de notre droite passant par l'abscisse de
			// l'autre droite
			return new Point.Double(other.point.x,
					(getSlope() * other.point.x + getB()));
		}
		// Si les deux droites sont parallèles, pas d'intersection.
		if (other.getSlope() == getSlope())
			return null;
		// Cas normal, on calcule les coordonnées du point normalement (système
		// de deux équations à deux inconnues).
		xInter = (other.getB() - getB()) / (getSlope() - other.getSlope());
		yInter = getSlope() * xInter + getB();
		return new Point.Double(xInter, yInter);

	}

	@Override
	public String toString() {
		return "Line [directionVectorX=" + director.x + ", directionVectorY="
				+ director.y + ", point=" + point + "]";
	}
	/**
	 * Renvoie la pente de la droite
	 * @return la pente de la droite.
	 */
	public double getSlope() {
		return director.y / director.x;
	}
	/**
	 * Renvoie l'ordonnée à l'origine de la droite.
	 * @return l'ordonnée à l'origine de la droite.
	 */
	public double getB() {
		return point.y - getSlope() * point.x;
	}

}
