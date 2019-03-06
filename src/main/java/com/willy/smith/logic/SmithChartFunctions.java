package com.willy.smith.logic;

import java.util.HashMap;
import java.util.Map;

import javafx.geometry.Point2D;

public class SmithChartFunctions {

	/**
	 * 
	 * @param rl standardized load resistance
	 * @return the function calculates the value of the radius and the center of the
	 *         resistance circle from the rl value
	 */
	public static Map<String, Double> calculateResCircle(double rl) {
		Map<String, Double> result = new HashMap<String, Double>();

		if (rl < 0.0)
			return null;

		double xc = rl / (1 + rl); // x-center
		double yc = 0; // y-center
		double radius = 1 / (1 + rl); // radio
		result.put("x-center", xc);
		result.put("y-center", yc);
		result.put("radius", radius);

		return result;
	}

	/**
	 * 
	 * @param xl standardized load reactance
	 * @return
	 */
	public static Map<String, Double> calculateReactCircle(double xl) {
		Map<String, Double> result = new HashMap<String, Double>();

		if (xl == 0.0) {
			return null;
		}

		double xc = 1.0;
		double yc = 1.0 / xl;
		double radius = Math.abs(1.0 / xl);
		result.put("x-center", xc);
		result.put("y-center", yc);
		result.put("radius", radius);

		return result;
	}

	public static Map<String, Point2D> circleCircleIntersection(Point2D p1, double r0, Point2D p2, double r1) {
		Map<String, Point2D> result = new HashMap<String, Point2D>();

		double x0 = p1.getX();
		double y0 = p1.getY();
		double x1 = p2.getX();
		double y1 = p2.getY();

		double a, dx, dy, d, h, rx, ry;
		double x2, y2;

		/*
		 * dx and dy are the vertical and horizontal distances between the circle
		 * centers.
		 */
		dx = x1 - x0;
		dy = y1 - y0;

		d = Math.hypot(dx, dy);

		/* Check for solvability. */
		if (d > (r0 + r1)) {
			/* no solution. circles do not intersect. */
			return null;
		}

		if (d < Math.abs(r0 - r1)) {
			/* no solution. one circle is contained in the other */
			return null;
		}
		
		/* Determine the distance from point 0 to point 2. */
		  a = ((r0*r0) - (r1*r1) + (d*d)) / (2.0 * d) ;
		  
		  /* Determine the coordinates of point 2. */
		  x2 = x0 + (dx * a/d);
		  y2 = y0 + (dy * a/d);
		  
		  /* Determine the distance from point 2 to either of the
		   * intersection points.
		   */
		  h = Math.sqrt((r0*r0) - (a*a));
		  
		  /* Now determine the offsets of the intersection points from
		   * point 2.
		   */
		  rx = -dy * (h/d);
		  ry = dx * (h/d);
		  
		  /* Determine the absolute intersection points. */
		  double xi = x2 + rx;
		  double xi_prime = x2 - rx;
		  double yi = y2 + ry;
		  double yi_prime = y2 - ry;
		  
		  result.put("pi-cross", new Point2D(xi, yi));
		  result.put("pi-prime-cross", new Point2D(xi_prime, yi_prime));

		return result;
	}

}

















