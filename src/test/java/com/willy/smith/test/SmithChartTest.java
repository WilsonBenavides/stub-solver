package com.willy.smith.test;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;

import com.willy.smith.chart.SmithChart;

public class SmithChartTest {
	
	@Test
	public void testCalculateResCircle() {
		SmithChart smithChart = new SmithChart();

		Map<String, Double> map = smithChart.calculateResCircle(0.5);
		
		assertThat(map.get("x-center"), is(closeTo(0.3333333334, 0.0000000001)));
		assertThat(map.get("y-center"), is(equalTo(0.0)));
		assertThat(map.get("radius"), is(closeTo(0.6666666667, 0.0000000001)));
		
	}

}
