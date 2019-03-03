package com.willy.smith.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.willy.smith.chart.SmithChart;
import com.willy.smith.exception.SmithChartException;

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
				{ 0.4, 0.2857142857, 0.0, 0.7142857143 }, { 0.5, 0.3333333334, 0.0, 0.6666666667 },
				{ 0.6, 0.375, 0.0, 0.625 }, { 0.7, 0.4117647059, 0.0, 0.5882352941 },
				{ 0.8, 0.4444444444, 0.0, 0.5555555556 }, { 0.9, 0.4736842105, 0.0, 0.5263157895 } };
		return Arrays.asList(data);
	}

	@Before
	public void initObjects() {
		smithChart = new SmithChart();
	}

	@Test(expected = SmithChartException.class)
	public void testCalculateResCircleExp() throws SmithChartException {

		Map<String, Double> map = smithChart.calculateResCircle(-1);
		System.out.println("Resistence: " + map.get("x-center"));
//		System.out.println("zero exception: " + map.get("x-center"));
//		throw new ArithmeticException();
//			System.out.println("zero exception: " + e.getMessage());
//			assertThat(e.getMessage(), is("/ by Zero"));
	}

	@Test
	public void testCalculateResCircle() throws SmithChartException {

		Map<String, Double> map = smithChart.calculateResCircle(rl);
		double delta = 0.0000000001;

		assertThat(map.get("x-center"), is(closeTo(xc, delta)));
		assertThat(map.get("y-center"), is(equalTo(yc)));
		assertThat(map.get("radius"), is(closeTo(radius, delta)));

	}

}
