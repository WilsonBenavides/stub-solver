package com.willy.smith.chart;

import static com.willy.smith.logic.SmithChartFunctions.calculateResCircle;
import static com.willy.smith.util.UtilConstant.RADIUS_RES_SCALE;
import static com.willy.smith.util.UtilConstant.SCREEN_SCALE;
import static javafx.scene.paint.Color.TRANSPARENT;

import java.io.IOException;

import com.willy.smith.util.SmithPoint;

import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class SmithChart extends Parent {

	private Rectangle2D screenBounds;

	public SmithChart(Rectangle2D screenBounds) throws IOException {
		this.screenBounds = screenBounds;

//		Circle cMax = drawCircleRes(0.0, DARKGREY);
		getChildren().addAll(drawSmithChart());
	}

	private Group drawSmithChart() throws IOException {
		Group gp = new Group();
		
		for (SmithPoint sp : SmithPoint.readCSV()) {
			Circle cc = drawCircleRes(sp.getResistance(), sp.getStrokeWidth(), sp.getStrokeColor());
			gp.getChildren().addAll(cc);
		}

//		for (Point2D val : values) {
//			Circle cc = drawCircleRes(val.getX(), val.getY(), DARKGREY);
//			gp.getChildren().addAll(cc);
//		}

//		for (int i = 0; i < 100; i += 1) {
//
//			if (i >= 0) {
//				Circle cc = drawCircleRes(Double.valueOf(i), DARKGREY);
//				gp.getChildren().addAll(cc);
//			}
//		}

		return gp;
	}

	/**
	 * 
	 * @param rl    standardized load resistance
	 * @param color circle resistance border color
	 * @return circle rl resistance circle with color border color
	 */
	private Circle drawCircleRes(Double rl, Double stroke, Color color) {

		if (rl < 0)
			return null;

		Double xc = calculateResCircle(rl).get("x-center");
		Double yc = calculateResCircle(rl).get("y-center");
		Double radius = calculateResCircle(rl).get("radius");

		Double scale = screenBounds.getHeight() * SCREEN_SCALE * RADIUS_RES_SCALE;
		Circle crcl = new Circle(xc * scale, yc * scale, radius * scale, TRANSPARENT);
		crcl.setStroke(color);
		crcl.setStrokeWidth(stroke);

		return crcl;
	}
}
