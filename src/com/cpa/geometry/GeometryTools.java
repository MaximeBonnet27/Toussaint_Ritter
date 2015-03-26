package com.cpa.geometry;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Classe utilitaire pour les calculs géometriques.
 * 
 * @author Maxime Bonnet
 * 
 */
public class GeometryTools {

	/**
	 * Renvoie la valeur absolue du cosinus de l'angle entre les deux lignes.
	 * 
	 * @param l1
	 *            Première ligne
	 * @param l2
	 *            Deuxième ligne
	 * @return La valeur absolue du cosinus de l'angle entre les vecteurs
	 *         directeurs des deux droites passées en paramètre.
	 */
	public static double cos(Line l1, Line l2) {
		double cos = l1.director.dotProduct(l2.director)
				/ (l1.director.magnitude() * l2.director.magnitude());
		// BLACK MAGIC
		return Math.abs(cos);
		//return cos;
	}

	/**
	 * Renvoie l'aire de l'enveloppe convexe passée en paramètre.
	 * 
	 * @param hull
	 *            L'enveloppe convexe
	 * @return L'aire de l'enveloppe convexe passée en paramètre.
	 */
	public static double getArea(ArrayList<Point.Double> hull) {
		double firstSum = 0;
		for (int i = 0; i < hull.size(); i++) {
			firstSum += hull.get(i).x * hull.get((i + 1) % hull.size()).y;
		}
		double secondSum = 0;
		for (int i = 0; i < hull.size(); i++) {
			secondSum += hull.get(i).y * hull.get((i + 1) % hull.size()).x;
		}
		return 0.5 * Math.abs((firstSum - secondSum));
	}

}
