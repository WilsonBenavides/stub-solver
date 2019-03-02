package com.willy.smith.test;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;

import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;

import com.willy.smith.chart.SmithChart;

public class SmithChartTest {
	
	@Test
	public void testCalculateResCircle() {
		SmithChart smithChart = new SmithChart();
		
		double radius = smithChart.calculateResCircle(0.5).get(2);
		
		assertThat(radius, is(closeTo(0.6666666667, 0.0000000001)));
		
	}

}
