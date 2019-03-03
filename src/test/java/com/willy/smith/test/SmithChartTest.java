package com.willy.smith.test;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.willy.smith.chart.SmithChart;

@RunWith(Parameterized.class)
public class SmithChartTest {

	private SmithChart smithChart;

	@Parameter(0)
	public Double rl;
	@Parameter(1)
	public Double xc;
	@Parameter(2)
	public Double yc;
	@Parameter(3)
	public Double radius;

	// creates the test data
	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] { { 0.1, 0.0909090909, 0.0, 0.9090909090 },
				{ 0.2, 0.1666666667, 0.0, 0.8333333334 }, { 0.3, 0.2307692308, 0.0, 0.7692307692 },
				{ 0.4, 0.2857142857, 0.0, 0.7142857143 }, { 0.5, 0.3333333334, 0.0, 0.6666666667 } };
		return Arrays.asList(data);
	}

	@Before
	public void initObjects() {
		smithChart = new SmithChart();
	}

	@Test
	public void testCalculateResCircle() {

		Map<String, Double> map = smithChart.calculateResCircle(rl);
		double delta = 0.0000000001;

		assertThat(map.get("x-center"), is(closeTo(xc, delta)));
		assertThat(map.get("y-center"), is(equalTo(yc)));
		assertThat(map.get("radius"), is(closeTo(radius, delta)));

	}

}
