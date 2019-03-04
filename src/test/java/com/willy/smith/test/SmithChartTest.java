package com.willy.smith.test;

import static com.willy.smith.logic.SmithChartFunctions.calculateReactCircle;
import static com.willy.smith.logic.SmithChartFunctions.calculateResCircle;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.willy.smith.exception.SmithChartException;

@RunWith(Enclosed.class)
public class SmithChartTest {

	@RunWith(Parameterized.class)
	public static class CalculateResParamTest {

		@Parameter(0)
		public Double rl;
		@Parameter(1)
		public Double rlxc;
		@Parameter(2)
		public Double rlyc;
		@Parameter(3)
		public Double rlRadius;
		@Parameter(4)
		public Double xl;
		@Parameter(5)
		public Double xlxc;
		@Parameter(6)
		public Double xlyc;
		@Parameter(7)
		public Double xlRadius;

		@Before
		public void initObjects() throws SmithChartException {
//			Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
//			smithChart = new SmithChart(screenBounds);
		}

		// creates the test data
		@Parameters
		public static Collection<Object[]> data() {
			Object[][] data = new Object[][] { { 0.1, 0.0909090909, 0.0, 0.9090909090, 0.2, 1.0, 5.0, 5.0 },
					{ 0.2, 0.1666666667, 0.0, 0.8333333334, 0.4, 1.0, 2.5, 2.5 },
					{ 0.3, 0.2307692308, 0.0, 0.7692307692, 0.6, 1.0, 1.6666666667, 1.6666666667 },
					{ 0.4, 0.2857142857, 0.0, 0.7142857143, 0.8, 1.0, 1.25, 1.25 },
					{ 0.5, 0.3333333334, 0.0, 0.6666666667, 1.0, 1.0, 1.0, 1.0 },
					{ 0.6, 0.375, 0.0, 0.625, 1.2, 1.0, 0.8333333333, 0.8333333333 },
					{ 0.7, 0.4117647059, 0.0, 0.5882352941, 1.4, 1.0, 0.7142857143, 0.7142857143 },
					{ 0.8, 0.4444444444, 0.0, 0.5555555556, 1.6, 1.0, 0.625, 0.625 },
					{ 0.9, 0.4736842105, 0.0, 0.5263157895, 1.8, 1.0, 0.5555555556, 0.5555555556} };
			return Arrays.asList(data);
		}

		@Test
		public void testCalculateResCircle() {

			Map<String, Double> map = calculateResCircle(rl);
			double delta = 0.0000000001;

			assertThat(map.get("x-center"), is(closeTo(rlxc, delta)));
			assertThat(map.get("y-center"), equalTo(rlyc));
			assertThat(map.get("radius"), is(closeTo(rlRadius, delta)));
		}

		@Test
		public void testCalculateReactCircle() {

			Map<String, Double> map = calculateReactCircle(xl);
			double delta = 0.0000000001;

			assertThat(map.get("x-center"), equalTo(xlxc));
			assertThat(map.get("y-center"), is(closeTo(xlyc, delta)));
			assertThat(map.get("radius"), is(closeTo(xlRadius, delta)));
		}
	}

//	public static class NotParametrizedTest {

//	}
}
