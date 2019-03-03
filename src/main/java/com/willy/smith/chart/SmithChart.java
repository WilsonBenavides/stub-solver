package com.willy.smith.chart;

import java.util.HashMap;
import java.util.Map;

import com.willy.smith.exception.SmithChartException;

public class SmithChart {

	/**
	 * 
	 * @param rl standardized load resistance
	 * @return the function calculates the value of the radius and the center of the
	 *         resistance circle from the rl value
	 */
	public Map<String, Double> calculateResCircle(double rl) throws SmithChartException {
		Map<String, Double> result = new HashMap<String, Double>();

		if (rl == -1.0)
			throw new SmithChartException("/ by Zero");

		double xc = rl / (1 + rl); // x-center
		double yc = 0; // y-center
		double radius = 1 / (1 + rl); // radio
		result.put("x-center", xc);
		result.put("y-center", yc);
		result.put("radius", radius);

		return result;
	}

}
