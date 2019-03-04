package com.willy.smith.chart;

import static com.willy.smith.logic.SmithChartFunctions.calculateReactCircle;
import static com.willy.smith.logic.SmithChartFunctions.calculateResCircle;
import static com.willy.smith.util.UtilConstant.RADIUS_RES_SCALE;
import static com.willy.smith.util.UtilConstant.SCREEN_SCALE;
import static javafx.scene.paint.Color.TRANSPARENT;

import java.io.IOException;
import java.util.ArrayList;

import static com.willy.smith.logic.SmithChartFunctions.circleCircleIntersection;
import com.willy.smith.util.SmithPoint;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class SmithChart extends Pane {

	private double scale;
	private double originX;
	private double originY;

	public SmithChart(Rectangle2D screenBounds) throws IOException {
		double offset = 8.0;
		this.scale = screenBounds.getHeight() * SCREEN_SCALE * RADIUS_RES_SCALE;
		this.originX = screenBounds.getWidth() * SCREEN_SCALE * RADIUS_RES_SCALE + offset;
		this.originY = scale + offset;

		getChildren().addAll(drawSmithChart());
	}

	private Group drawSmithChart() throws IOException {
		Group gp = new Group();

		ArrayList<Circle> sps = new ArrayList<Circle>();
		for (SmithPoint sp : SmithPoint.readResCSV()) {
			Circle res = drawCircleRes(sp.getResistance(), sp.getStrokeWidthRes(), sp.getStrokeColorRes());
			sps.add(res);
			gp.getChildren().addAll(res);
		}

		for (SmithPoint sp : SmithPoint.readReactCSV()) {
			Circle react = drawCircleReact(sp.getReactance(), sp.getStrokeWidthReact(), sp.getStrokeColorReact());
			sps.add(react);
			gp.getChildren().addAll(react);
		}
		
		double x0 = sps.get(0).getCenterX();
		double y0 = sps.get(0).getCenterY();
		double r0 = sps.get(0).getRadius();
		
		double x1 = sps.get(1).getCenterX();
		double y1 = sps.get(1).getCenterY();
		double r1 = sps.get(1).getRadius();
		
		Point2D cross1 = circleCircleIntersection(new Point2D(x0, y0), r0, new Point2D(x1, y1), r1).get("pi-cross");
		Point2D cross2 = circleCircleIntersection(new Point2D(x0, y0), r0, new Point2D(x1, y1), r1).get("pi-prime-cross");
		
		gp.getChildren().add(new Circle(cross1.getX(), cross1.getY(), 5, Color.DARKRED));
		gp.getChildren().add(new Circle(cross2.getX(), cross2.getY(), 5, Color.DARKRED));
		
		return gp;
	}

	/**
	 * 
	 * @param xl
	 * @param stroke
	 * @param color
	 * @return
	 */
	private Circle drawCircleReact(double xl, double stroke, Color color) {
		if (xl == 0.0)
			return null;

		double xc = calculateReactCircle(xl).get("x-center");
		double yc = calculateReactCircle(xl).get("y-center");
		double radius = calculateReactCircle(xl).get("radius");

		Circle crcl = new Circle(originX + xc * scale, originY + yc * scale, radius * scale, TRANSPARENT);
		crcl.setStroke(color);
		crcl.setStrokeWidth(stroke);

		return crcl;
	}

	/**
	 * 
	 * @param rl    standardized load resistance
	 * @param color circle resistance border color
	 * @return circle rl resistance circle with color border color
	 */
	private Circle drawCircleRes(double rl, double stroke, Color color) {

		if (rl < 0)
			return null;

		double xc = calculateResCircle(rl).get("x-center");
		double yc = calculateResCircle(rl).get("y-center");
		double radius = calculateResCircle(rl).get("radius");

		Circle crcl = new Circle(originX + xc * scale, originY + yc * scale, radius * scale, TRANSPARENT);
		crcl.setStroke(color);
		crcl.setStrokeWidth(stroke);

		return crcl;
	}
}
