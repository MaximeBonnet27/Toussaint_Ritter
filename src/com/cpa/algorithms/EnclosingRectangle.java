package com.cpa.algorithms;

import java.awt.Point;
import java.util.ArrayList;

import com.cpa.geometry.GeometryTools;
import com.cpa.geometry.Line;
import com.cpa.geometry.Rectangle;
import com.cpa.geometry.Vector;

/**
 * Classe comprenant les opérations nécessaires pour trouver le rectangle
 * minimum. source : calipers.pdf
 * 
 * @author Maxime Bonnet
 * 
 */
public class EnclosingRectangle {
  /**
   * L'algorithme de Toussaint.
   * 
   * @param hull
   *          L'enveloppe convexe du nuage de points considéré
   * @return Le rectangle d'aire minimum comprennant tous les points du nuage.
   * @throws IllegalArgumentException
   *           Si l'enveloppe est trop petite ou si des points extremes sont
   *           confondus.
   */
  public static Rectangle computeToussaint(ArrayList<Point.Double> hull)
      throws IllegalArgumentException {

    // On ne pourra pas faire le rectangle minimum si l'enveloppe n'a pas 4
    // points.
    if (hull.size() < 4) {
      throw new IllegalArgumentException("L'enveloppe convexe ne contient que "
          + hull.size() + " points.");
    }

    // Première étape : On cherche les quatre points extremes (abscisses
    // minimum & maximum, ordonnées minimum & maximum)

    // On garde en mémoire les indexs des points i, j, k et l.
    int index_i = 0, index_j = 0, index_k = 0, index_l = 0;
    // Première position des points, utilisée pour vérifier quand on a fait
    // un tour complet.
    int index_i0, index_j0, index_k0, index_l0;
    for (int i = 1; i < hull.size(); i++) {
      // i est le point d'abscisse minimum
      if (hull.get(i).x < hull.get(index_i).x) {
        index_i = i;
      }
      // l est le point d'ordonnée minimum
      if (hull.get(i).y < hull.get(index_l).y) {
        index_l = i;
      }
      // k est le point d'abscisse maximum
      if (hull.get(i).x > hull.get(index_k).x) {
        index_k = i;
      }
      // j est le point d'ordonnée maximum
      if (hull.get(i).y > hull.get(index_j).y) {
        index_j = i;
      }
    }
    // Si deux points extrêmes sont confondus, l'algorithme de Toussaint ne
    // marchera pas.
    if (index_i == index_j || index_i == index_k || index_i == index_l
        || index_j == index_k || index_j == index_l || index_k == index_l) {
      throw new IllegalArgumentException("Points extremes confondus");
    }

    index_i0 = index_i;
    index_j0 = index_j;
    index_k0 = index_k;
    index_l0 = index_l;

    // Deuxième étape : On initialise les lignes de support (lignes passant
    // par les 4 points i, j, k et l)
    Line support_i, support_j, support_k, support_l;
    support_i = new Line(hull.get(index_i), new Vector(0, 1));
    support_j = new Line(hull.get(index_j), new Vector(1, 0));
    support_k = new Line(hull.get(index_k), new Vector(0, -1));
    support_l = new Line(hull.get(index_l), new Vector(-1, 0));

    // Condition d'arrêt
    boolean hullScanned = false;
    // Auxiliaire à la condition d'arrêt, vérifie si le point concerné à
    // bien avancé.
    boolean iStepped = false, jStepped = false, kStepped = false, lStepped = false;
    // Le nombre de fois que l'un des points i, j, k ou l passe par sa
    // position initiale
    int count = 0;

    // Les cosinus entre les lignes de support et le prochain coté de
    // l'enveloppe convexe
    double cos_theta_i, cos_theta_j, cos_theta_k, cos_theta_l;
    // La valeur maximum de ces cosinus (correspond à l'angle minimum)
    double cos_theta_max;
    int index_cos_max;
    // Le rectangle resultat
    Rectangle res = new Rectangle(support_i, support_j, support_k, support_l);
    // L'aire du rectangle resultat
    double areaMin = Double.MAX_VALUE;
    // Le rectangle actuel
    Rectangle rect = res;
    // Tant que l'on n'a pas fini de parcourir l'enveloppe convexe.
    while (!hullScanned) {
      /*
       * Pour chaque point, on calcule le cosinus entre sa ligne de support et
       * le prochain coté dans l'enveloppe convexe. De plus, on garde en mémoire
       * la valeur du cosinus maximum, et l'index du point corresepondant.
       */
      cos_theta_i = GeometryTools.cos(support_i, new Line(hull.get(index_i),
          hull.get((index_i + 1) % hull.size())));
      cos_theta_j = GeometryTools.cos(support_j, new Line(hull.get(index_j),
          hull.get((index_j + 1) % hull.size())));
      if (cos_theta_i > cos_theta_j) {
        cos_theta_max = cos_theta_i;
        index_cos_max = index_i;
      } else {
        cos_theta_max = cos_theta_j;
        index_cos_max = index_j;
      }
      cos_theta_k = GeometryTools.cos(support_k, new Line(hull.get(index_k),
          hull.get((index_k + 1) % hull.size())));
      if (cos_theta_k > cos_theta_max) {
        cos_theta_max = cos_theta_k;
        index_cos_max = index_k;
      }
      cos_theta_l = GeometryTools.cos(support_l, new Line(hull.get(index_l),
          hull.get((index_l + 1) % hull.size())));
      if (cos_theta_l > cos_theta_max) {
        cos_theta_max = cos_theta_l;
        index_cos_max = index_l;
      }

      /*
       * Quatrième étape : On fait avancer le point correspondant à l'angle
       * minimum. Sa ligne support est alors confondue avec le prochain coté de
       * l'enveloppe convexe, et on détermine les trois autres lignes de support
       * en fonction de celle-ci (orthogonale / inverse)
       */

      if (index_cos_max == index_i) {
        support_i = new Line(hull.get(index_i), hull.get((index_i + 1)
            % hull.size()));
        support_j = new Line(hull.get(index_j), support_i.director.normal()
            .invert());
        support_k = new Line(hull.get(index_k), support_i.director.invert());
        support_l = new Line(hull.get(index_l), support_j.director.invert());

        index_i = (index_i + 1) % hull.size();
        support_i.point = hull.get(index_i);
        // Le point i a bougé (condition d'arrêt)
        iStepped = true;
      } else if (index_cos_max == index_j) {
        support_j = new Line(hull.get(index_j), hull.get((index_j + 1)
            % hull.size()));
        support_k = new Line(hull.get(index_k), support_j.director.normal()
            .invert());
        support_l = new Line(hull.get(index_l), support_j.director.invert());
        support_i = new Line(hull.get(index_i), support_k.director.invert());

        index_j = (index_j + 1) % hull.size();
        support_j.point = hull.get(index_j);
        // Le point j a bougé (condition d'arrêt)
        jStepped = true;

      } else if (index_cos_max == index_k) {
        support_k = new Line(hull.get(index_k), hull.get((index_k + 1)
            % hull.size()));
        support_l = new Line(hull.get(index_l), support_k.director.normal()
            .invert());
        support_i = new Line(hull.get(index_i), support_k.director.invert());
        support_j = new Line(hull.get(index_j), support_l.director.invert());
        index_k = (index_k + 1) % hull.size();
        support_k.point = hull.get(index_k);
        // Le piont k a bougé (condition d'arrêt)
        kStepped = true;

      } else {

        support_l = new Line(hull.get(index_l), hull.get((index_l + 1)
            % hull.size()));
        support_i = new Line(hull.get(index_i), support_l.director.normal()
            .invert());
        support_j = new Line(hull.get(index_j), support_l.director.invert());
        support_k = new Line(hull.get(index_k), support_i.director.invert());
        index_l = (index_l + 1) % hull.size();
        support_l.point = hull.get(index_l);
        // Le point l a bougé (condition d'arrêt)
        lStepped = true;

      }

      // Cinquième étape : On calcule l'aire du rectangle.

      rect = new Rectangle(support_i, support_j, support_k, support_l);
      // Mise à jour du rectangle minimum
      if (rect.area() < areaMin) {
        res = rect;
        areaMin = rect.area();
      }
      // Si l'un des points a bougé et qu'il est revenu à son point de
      // départ
      if (iStepped && index_i == index_i0 || jStepped && index_j == index_j0
          || kStepped && index_k == index_k0 || lStepped && index_l == index_l0) {
        // On incrémente le compteur.
        count++;
      }
      // Condition d'arrêt
      hullScanned = (count >= 4);
    }
    // On renvoie le plus petit rectangle
    return res;
  }
}
