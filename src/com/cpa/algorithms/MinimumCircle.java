package com.cpa.algorithms;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import com.cpa.geometry.Circle;

/**
 * Classe comprenant l'implantation de l'algorithme de Ritter pour trouver le
 * cercle d'aire minimum comprennant un nuage de points
 * 
 * @author Maxime Bonnet
 * 
 */
public class MinimumCircle {
	/**
	 * Méthode principale, application de l'algorithme de Ritter au nuage de
	 * points passé en paramètre.
	 * 
	 * @param points
	 *            Le nuage de points.
	 * @return Une approximation du cercle minimum.
	 */
	public static Circle computeRitter(ArrayList<Point.Double> points) {

		// On prend un point dummy quelconque dans le nuage de points.
		Random random = new Random();
		Point.Double dummy = points.get(random.nextInt(points.size()));
		double distance = Integer.MIN_VALUE;
		// p est le point le plus éloigné de dummy dans le nuage de points.
		Point.Double p = null;
		for (Point.Double temp : points) {
			double d = dummy.distance(temp);
			if (d >= distance) {
				distance = d;
				p = temp;
			}
		}
		// q est le point le plus éloigné de p dans le nuage de points.
		Point.Double q = null;
		for (Point.Double temp : points) {
			double d = p.distance(temp);
			if (d >= distance) {
				distance = d;
				q = temp;
			}
		}

		Point.Double c, c2 = null;
		double cp = 0;

		// Coordonnées du centre du segment [PQ]
		double a = (p.x + q.x) / 2;
		double b = (p.y + q.y) / 2;
		// c est le centre du segment [PQ]
		c = new Point.Double(a, b);
		// Rayon du cercle de centre c, passant par P et Q (distance CP)
		cp = Point.Double.distance(a, b, p.x, p.y);
		// On parcourt la liste de points, et on enlève tout point compris dans
		// ce cercle
		ArrayList<Point.Double> l = new ArrayList<Point.Double>();
		for (Point.Double temp : points) {
			if (Point.Double.distance(a, b, temp.x, temp.y) > cp) {
				l.add(temp);
			}
		}
		// S'il ne reste plus de points dans la liste
		if (l.isEmpty()) {
			// On renvoie le cercle centré en C, et de rayon CP
			return new Circle(c, cp);
		}
		// S'il en reste, soit s un de ces points
		Point.Double s = null;
		// Tant que la liste n'est pas vide
		while (!l.isEmpty()) {
			ArrayList<Point.Double> l1 = new ArrayList<Point.Double>();
			// S est un point quelconque dans la liste des points.
			s = l.get(random.nextInt(l.size()));
			// On l'enlève de la liste.
			l.remove(s);
			// 8.

			double alpha, beta;
			// Distance du centre du cercle à S
			double sc = Point.Double.distance(s.x, s.y, a, b);
			// T est sur le cercle,
			double st = sc + cp;
			double sc2 = st / 2;
			double cc2 = sc - sc2;
			alpha = sc2 / sc;
			beta = cc2 / sc;

			// 9.
			double a2 = alpha * a + beta * s.x;
			double b2 = alpha * b + beta * s.y;
			c2 = new Point.Double(a2, b2);
			for (Point.Double temp : l) {
				if (Point.Double.distance(a2, b2, temp.x, temp.y) > sc2) {
					l1.add(temp);
				}
			}
			if (l1.isEmpty()) {
				return new Circle(c2, sc2);
			} else {
				l = (ArrayList<Point.Double>) l1.clone();
				a = a2;
				b = b2;
				cp = sc2;
			}
			// 10.

		}
		return new Circle(c2, cp);

	}

	/**
	 * Calcul naif du cercle minimum
	 * 
	 * @param points
	 * @return Le cercle minimum englobant tous les points de la liste passee en
	 *         parametre.
	 */
	public static Circle naif(ArrayList<Point.Double> points) {
		double distanceMax = -1, distance = 0;
		Point.Double centre = new Point.Double();
		for(int i = 0; i < points.size(); ++i){
			for(int j = i+1; j< points.size(); ++j){
				distance = points.get(i).distance(points.get(j));
				if(distance > distanceMax){
					distanceMax = distance;
					centre.x = (points.get(i).x + points.get(j).y) / 2;
					centre.y = (points.get(i).y + points.get(j).y) / 2;
				}
			}
		}
		return new Circle(centre, distanceMax / 2);
	}

}
