package com.cpa.algorithms;

import java.util.ArrayList;

import com.cpa.geometry.GeometryTools;
import com.cpa.geometry.Line;
import com.cpa.geometry.Rectangle;
import com.cpa.geometry.Vector;
import com.cpa.geometry.Vertex;
import com.cpa.view.AlgorithmCanvas;

public class EnclosingRectangle {

	public static Rectangle computeToussaint(ArrayList<Vertex> hull) {
		// 1: Find the 4 extreme points.

		int index_i = 0, index_j = 0, index_k = 0, index_l = 0;
		int index_i0, index_j0, index_k0, index_l0;
		for (int i = 1; i < hull.size(); i++) {
			if (hull.get(i).x < hull.get(index_i).x) {
				index_i = i;
			}
			if (hull.get(i).y < hull.get(index_l).y) {
				index_l = i;
			}
			if (hull.get(i).x > hull.get(index_k).x) {
				index_k = i;
			}
			if (hull.get(i).y > hull.get(index_j).y) {
				index_j = i;
			}

		}

		index_i0 = index_i;
		index_j0 = index_j;
		index_k0 = index_k;
		index_l0 = index_l;
		// 2 : Create the lines of support.
		Line support_i, support_j, support_k, support_l;
		support_i = new Line(hull.get(index_i), new Vector(0, 1));
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
		Rectangle rect = res;
		while (!hullScanned && i++ < 200000) {
			/*System.out.println("I : " + index_i);
			System.out.println("J : " + index_j);
			System.out.println("K : " + index_k);
			System.out.println("L : " + index_l);
			*/
			/*
			if(index_i == index_j || index_i == index_k || index_i == index_l){
				System.out.println("IIINN YOUR FACE");
				System.out.println(index_i);
			}
			if(index_j == index_k || index_j == index_l){
				System.out.println("JJJN YOUR FACE");
				System.out.println(index_j);
			}
			if(index_k == index_l){
				System.out.println("KKKN YOUR FACE");
				System.out.println(index_k);
			}
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
			// 4 : The min point steps on.
			if (index_cos_max == index_i) {
				support_i = new Line(hull.get(index_i), hull.get((index_i + 1)
						% hull.size()));
				support_j = new Line(hull.get(index_j),
						support_i.director.normal());
				support_k = new Line(hull.get(index_k),
						support_j.director.normal());
				support_l = new Line(hull.get(index_l), support_k.director
						.normal());

				index_i = (index_i + 1) % hull.size();
				support_i.point = hull.get(index_i);

				/*if(index_i == index_j){
          index_j = (index_j + 1) % hull.size();
          support_j.point = hull.get(index_j);
        }else if(index_i == index_k){
          index_k = (index_k + 1) % hull.size();
          support_k.point = hull.get(index_k);
        }else if(index_i == index_l){
          index_l = (index_l + 1) % hull.size();
          support_l.point = hull.get(index_l);
        }
				 */
				iStepped = true;
			} else if (index_cos_max == index_j) {
				support_j = new Line(hull.get(index_j), hull.get((index_j + 1)
						% hull.size()));
				support_k = new Line(hull.get(index_k),
						support_j.director.normal());
				support_l = new Line(hull.get(index_l),
						support_k.director.normal());
				support_i = new Line(hull.get(index_i), support_l.director
						.normal());

				index_j = (index_j + 1) % hull.size();
				support_j.point = hull.get(index_j);

				/*if(index_j == index_k){
          index_k = (index_k + 1) % hull.size();
          support_k.point = hull.get(index_k);
        }else if(index_j == index_l){
          index_l = (index_l + 1) % hull.size();
          support_l.point = hull.get(index_l);
        } 
        else if(index_j == index_i){
          index_i = (index_i + 1) % hull.size();
          support_i.point = hull.get(index_i);
        }
				 */
				jStepped = true;

			} else if (index_cos_max == index_k) {
				support_k = new Line(hull.get(index_k), hull.get((index_k + 1)
						% hull.size()));
				support_l = new Line(hull.get(index_l),
						support_k.director.normal());
				support_i = new Line(hull.get(index_i),
						support_l.director.normal());
				support_j = new Line(hull.get(index_j), support_i.director
						.normal());
				index_k = (index_k + 1) % hull.size();
				support_k.point = hull.get(index_k);
				/*
        if(index_k == index_j){
          index_j = (index_j + 1) % hull.size();
          support_j.point = hull.get(index_j);
        }else if(index_k == index_i){
          index_i = (index_i + 1) % hull.size();
          support_i.point = hull.get(index_i);
        }else if(index_k == index_l){
          index_l = (index_l + 1) % hull.size();
          support_l.point = hull.get(index_l);
        }
				 */
				kStepped = true;

			} else {

				support_l = new Line(hull.get(index_l), hull.get((index_l + 1)
						% hull.size()));
				support_i = new Line(hull.get(index_i),
						support_l.director.normal());
				support_j = new Line(hull.get(index_j),
						support_i.director.normal());
				support_k = new Line(hull.get(index_k), support_j.director
						.normal());
				index_l = (index_l + 1) % hull.size();
				support_l.point = hull.get(index_l);
				/*
        if(index_l == index_j){
          index_j = (index_j + 1) % hull.size();
          support_j.point = hull.get(index_j);
        }else if(index_l == index_k){
          index_k = (index_k + 1) % hull.size();
          support_k.point = hull.get(index_k);
        }else if(index_l == index_i){
          index_i = (index_i + 1) % hull.size();
          support_i.point = hull.get(index_i);
        }
				 */
				lStepped = true;

			}
			
			/*System.out.println("cosI " + cos_theta_i);
			System.out.println("cosJ " + cos_theta_j);
			System.out.println("cosK " + cos_theta_k);
			System.out.println("cosL " + cos_theta_l);
			System.out.println();
			*/
			// 5 : Get rectangle's area.
			rect = new Rectangle(support_i, support_j, support_k,
					support_l);

			if (rect.area() < areaMin) {
				res = rect;
				areaMin = rect.area();
			}

			if (iStepped && index_i == index_i0 || jStepped
					&& index_j == index_j0 || kStepped && index_k == index_k0
					|| lStepped && index_l == index_l0) {
				count++;
			}
			hullScanned = (count >= 4);
		}
		return res;
	}

}
