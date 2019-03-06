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
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
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

			Circle t1 = drawCircleRes(1.0, sp.getStrokeWidthRes(), sp.getStrokeColorRes());
			Circle t2 = drawCircleReact(1.0, sp.getStrokeWidthRes(), sp.getStrokeColorRes());
			Point2D css1 = circleCircleIntersection(new Point2D(t1.getCenterX(), t1.getCenterY()), t1.getRadius(),
					new Point2D(t2.getCenterX(), t2.getCenterY()), t2.getRadius()).get("pi-prime-cross");
			Circle pnt3 = new Circle(css1.getX(), css1.getY(), 5, Color.CADETBLUE);

			double xl1 = 0.5;
			double xl2 = -0.5;

			Circle react1 = drawCircleReact(xl1, sp.getStrokeWidthRes(), sp.getStrokeColorRes());
			Circle react2 = drawCircleReact(xl2, sp.getStrokeWidthRes(), sp.getStrokeColorRes());

			Arc resArc = drawArcRes(sp.getResistance(), xl1, xl2, sp.getStrokeWidthRes(), sp.getStrokeColorRes());

			double x0 = react1.getCenterX();
			double y0 = react1.getCenterY();
			double r0 = react1.getRadius();
			double x00 = react2.getCenterX();
			double y00 = react2.getCenterY();
			double r00 = react2.getRadius();

			double x1 = res.getCenterX();
			double y1 = res.getCenterY();
			double r1 = res.getRadius();

			Point2D cross1 = circleCircleIntersection(new Point2D(x0, y0), r0, new Point2D(x1, y1), r1)
					.get("pi-prime-cross");
			Point2D cross3 = circleCircleIntersection(new Point2D(x0, y0), r0, new Point2D(x1, y1), r1).get("pi-cross");
			Point2D cross2 = circleCircleIntersection(new Point2D(x00, y00), r00, new Point2D(x1, y1), r1)
					.get("pi-prime-cross");
			Point2D cross4 = circleCircleIntersection(new Point2D(x00, y00), r00, new Point2D(x1, y1), r1)
					.get("pi-cross");

			System.out.println("(x1: " + cross3.getX() + " , y1: " + cross3.getY() + ")  (x2: " + cross4.getX()
					+ " , y2: " + cross4.getY());

			Circle pnt1 = new Circle(cross1.getX(), cross1.getY(), 5, Color.DARKRED);
			Circle pnt2 = new Circle(cross2.getX(), cross2.getY(), 5, Color.DARKGREEN);

			sps.add(res);
			gp.getChildren().addAll(res, resArc, react1, react2, pnt1, pnt2, pnt3);
		}

		for (SmithPoint sp : SmithPoint.readReactCSV()) {
			Circle react = drawCircleReact(sp.getReactance(), sp.getStrokeWidthReact(), sp.getStrokeColorReact());
			sps.add(react);
//			gp.getChildren().addAll(react);
		}

		return gp;
	}

	private Arc drawArcRes(double rl, double xl1, double xl2, double strokeWidth, Color strokeColor) {

		Circle res = drawCircleRes(rl, strokeWidth, strokeColor);

		Circle react1 = drawCircleReact(xl1, strokeWidth, strokeColor);
		Circle react2 = drawCircleReact(xl2, strokeWidth, strokeColor);

		Circle t1 = drawCircleRes(1.0, 1.5, strokeColor);
		Circle t2 = drawCircleReact(1.0, 1.5, strokeColor);
		Point2D css1 = circleCircleIntersection(new Point2D(t1.getCenterX(), t1.getCenterY()), t1.getRadius(),
				new Point2D(t2.getCenterX(), t2.getCenterY()), t2.getRadius()).get("pi-prime-cross");
		Circle pnt3 = new Circle(css1.getX(), css1.getY(), 5, Color.CADETBLUE);

		double x0 = react1.getCenterX();
		double y0 = react1.getCenterY();
		double r0 = react1.getRadius();
		double x00 = react2.getCenterX();
		double y00 = react2.getCenterY();
		double r00 = react2.getRadius();

		double x1 = res.getCenterX();
		double y1 = res.getCenterY();
		double r1 = res.getRadius();

		Point2D cross1 = circleCircleIntersection(new Point2D(x0, y0), r0, new Point2D(x1, y1), r1)
				.get("pi-prime-cross");
		Point2D cross3 = circleCircleIntersection(new Point2D(x0, y0), r0, new Point2D(x1, y1), r1).get("pi-cross");
		
		Point2D cross2 = circleCircleIntersection(new Point2D(x00, y00), r00, new Point2D(x1, y1), r1)
				.get("pi-prime-cross");
		Point2D cross4 = circleCircleIntersection(new Point2D(x00, y00), r00, new Point2D(x1, y1), r1).get("pi-cross");
		
		System.out.println("cross 1 : " + pnt3.contains(cross1));
		System.out.println("cross 2 : " + pnt3.contains(cross2));
		System.out.println("cross 3 : " + pnt3.contains(cross3));
		System.out.println("cross 4 : " + pnt3.contains(cross4));
		
		cross1 = pnt3.contains(cross1) ? cross3 : cross1;
		cross2 = pnt3.contains(cross2) ? cross4 : cross2;

		double theta1 = Math.toDegrees(Math.atan2(res.getCenterY() - cross1.getY(), cross1.getX() - res.getCenterX()));
		theta1 = (theta1 < 0.0) ? 360.0 + theta1 : theta1;
		double theta2 = Math.toDegrees(Math.atan2(res.getCenterY() - cross2.getY(), cross2.getX() - res.getCenterX()));
		theta2 = (theta2 < 0.0) ? 360.0 + theta2 : theta2;

		double length = Math.abs(theta2 - theta1);
		
		double tht = theta2 < theta1 ? theta2 : theta1;

//		System.out.println("theta 1 : " + theta1 + " , theta 2 : " + theta2);

		Arc arc = new Arc();
		arc.setCenterX(res.getCenterX());
		arc.setCenterY(res.getCenterY());
		arc.setRadiusX(res.getRadius());
		arc.setRadiusY(res.getRadius());
		arc.setStartAngle(tht);
		arc.setLength(length);
		arc.setFill(Color.TRANSPARENT);
		arc.setStroke(Color.INDIANRED);
		arc.setType(ArcType.OPEN);

		return arc;
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
