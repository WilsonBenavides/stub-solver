package com.willy.smith.chart;

import java.util.ArrayList;

public class SmithChart {

	/**
	 * 
	 * @param rl standardized load resistance
	 * @return the function calculates the value of the radius and the center of the resistance circle from the rl value
	 */
	public ArrayList<Double> calculateResCircle(double rl) {
		ArrayList<Double> result = new ArrayList<Double>();

		double xc = rl / (1 + rl); // x-center
		double yc = 0; // y-center
		double radius = 1 / (1 + rl); // radio

		result.add(xc);
		result.add(yc);
		result.add(radius);

		return result;
	}

}
