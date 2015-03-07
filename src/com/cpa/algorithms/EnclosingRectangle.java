package com.cpa.algorithms;

import java.util.ArrayList;

import com.cpa.geometry.GeometryTools;
import com.cpa.geometry.Line;
import com.cpa.geometry.Rectangle;
import com.cpa.geometry.Vector;
import com.cpa.geometry.Vertex;

public class EnclosingRectangle {

	public static ArrayList<Vertex> computeToussaint(ArrayList<Vertex> hull) {

		// 1: Find the 4 extreme points.

		int index_i = 0, index_j = 0, index_k = 0, index_l = 0;
		int index_i0, index_j0, index_k0, index_l0;
		for (int i = 1; i < hull.size(); i++) {
			if (hull.get(i).x < hull.get(index_i).x) {
				index_i = i;
			}
			if (hull.get(i).y < hull.get(index_j).y) {
				index_j = i;
			}
			if (hull.get(i).x > hull.get(index_k).x) {
				index_k = i;
			}
			if (hull.get(i).y > hull.get(index_l).y) {
				index_l = i;
			}

		}

		index_i0 = index_i;
		index_j0 = index_j;
		index_k0 = index_k;
		index_l0 = index_l;

		// 2 : Create the lines of support.
		Line support_i, support_j, support_k, support_l;
		support_i = new Line(hull.get(index_i), new Vector(0, -1));
		support_j = new Line(hull.get(index_j), support_i.director.normal());
		support_k = new Line(hull.get(index_k), support_j.director.normal());
		support_l = new Line(hull.get(index_l), support_k.director.normal());

		// 3 : Get the angles.

		boolean hullScanned = false;
		boolean iStepped = false, jStepped = false, kStepped = false, lStepped = false;
		int count = 0;

		double cos_theta_i, cos_theta_j, cos_theta_k, cos_theta_l;
		double cos_theta_max;
		int index_cos_max;
		Rectangle res = new Rectangle(support_i, support_j, support_k,
				support_l);
		double areaMin = Double.MAX_VALUE;
		int i = 0;
		while (!hullScanned && ++i < 1) {

			cos_theta_i = GeometryTools.cos(support_i, new Line(hull.get(index_i),
					hull.get((index_i + 1) % hull.size())));
			cos_theta_j = GeometryTools.cos(support_j, new Line(hull.get(index_j),
					hull.get((index_j + 1) % hull.size())));
			if (Math.abs(cos_theta_i) > Math.abs(cos_theta_j)) {
				cos_theta_max = cos_theta_i;
				index_cos_max = index_i;
			} else {
				cos_theta_max = cos_theta_j;
				index_cos_max = index_j;
			}
			cos_theta_k = GeometryTools.cos(support_k, new Line(hull.get(index_k),
					hull.get((index_k + 1) % hull.size())));
			if (Math.abs(cos_theta_k) > cos_theta_max) {
				cos_theta_max = cos_theta_k;
				index_cos_max = index_k;
			}
			cos_theta_l = GeometryTools.cos(support_l, new Line(hull.get(index_l),
					hull.get((index_l + 1) % hull.size())));
			if (Math.abs(cos_theta_l) > cos_theta_max) {
				cos_theta_max = cos_theta_l;
				index_cos_max = index_l;
			}

			// 4 : The min point steps on.

			if (index_cos_max == index_i) {
				index_i = (index_i + 1) % hull.size();
				support_i.point = hull.get(index_i);

				support_i = new Line(hull.get(index_i), hull.get((index_i + 1)
						% hull.size()));
				support_j = new Line(hull.get(index_j),
						support_i.director.normal());
				support_k = new Line(hull.get(index_k),
						support_i.director.invert());
				support_l = new Line(hull.get(index_l), support_i.director
						.normal().invert());
				iStepped = true;
			} else if (index_cos_max == index_j) {
				index_j = (index_j + 1) % hull.size();
				support_j.point = hull.get(index_j);

				support_j = new Line(hull.get(index_j), hull.get((index_j + 1)
						% hull.size()));
				support_k = new Line(hull.get(index_k),
						support_j.director.normal());
				support_l = new Line(hull.get(index_l),
						support_j.director.invert());
				support_i = new Line(hull.get(index_i), support_j.director
						.normal().invert());
				jStepped = true;

			} else if (index_cos_max == index_k) {
				index_k = (index_k + 1) % hull.size();
				support_k.point = hull.get(index_k);

				support_k = new Line(hull.get(index_k), hull.get((index_k + 1)
						% hull.size()));
				support_l = new Line(hull.get(index_l),
						support_k.director.normal());
				support_i = new Line(hull.get(index_i),
						support_k.director.invert());
				support_j = new Line(hull.get(index_j), support_k.director
						.normal().invert());
				kStepped = true;

			} else {
				index_l = (index_l + 1) % hull.size();
				support_l.point = hull.get(index_l);

				support_l = new Line(hull.get(index_l), hull.get((index_l + 1)
						% hull.size()));
				support_i = new Line(hull.get(index_i),
						support_l.director.normal());
				support_j = new Line(hull.get(index_j),
						support_l.director.invert());
				support_k = new Line(hull.get(index_k), support_l.director
						.normal().invert());
				lStepped = true;

			}
			// 5 : Get rectangle's area.
			Rectangle rect = new Rectangle(support_i, support_j, support_k,
					support_l);
			if (rect.area() < areaMin) {
				res = rect;
				areaMin = rect.area();
				System.out.println(areaMin);
			}

			if (iStepped && index_i == index_i0 || jStepped
					&& index_j == index_j0 || kStepped && index_k == index_k0
					|| lStepped && index_l == index_l0) {
				count++;
			}
			hullScanned = (count >= 4);
		}
		return res.getPoints();
	}

}
