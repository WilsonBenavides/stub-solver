package com.willy.smith.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVReader;

import javafx.scene.paint.Color;

public class SmithPoint {

	private static final String CSV_FILE_RES = "src/main/resources/plot_res_values_excel.csv";
	private static final String CSV_FILE_REACT = "src/main/resources/plot_react_values_excel.csv";
	
	private Double resistance;
	private Double strokeWidthRes;
	private Color strokeColorRes;
	private Double reactance;
	private Double strokeWidthReact;
	private Color strokeColorReact;
	
	public SmithPoint() {}

	public SmithPoint(Double resistance, Double strokeWidth, Color strokeColor) {
		this.resistance = resistance;
		this.strokeWidthRes = strokeWidth;
		this.strokeColorRes = strokeColor;
	}
	
	public SmithPoint(double reactance, double strokeWidthReact, Color strokeColorReact, boolean flag) {
		this.reactance = reactance;
		this.strokeWidthReact = strokeWidthReact;
		this.strokeColorReact = strokeColorReact;
//		System.out.println("flag value: " + flag);
	}
	
	@SuppressWarnings({ "deprecation", "resource" })
	public static ArrayList<SmithPoint> readResCSV() throws IOException {
		CSVReader reader = new CSVReader(new FileReader(CSV_FILE_RES), ';');

        // read line by line
        String[] nextLine;
        ArrayList<SmithPoint> points = new ArrayList<SmithPoint>();

        boolean isFirst = true;
        while ((nextLine = reader.readNext()) != null) {
        	if (!isFirst) {
        		// nextLine[] is an array of values from the line
//        		System.out.println("values: " + nextLine[0] + " , " + nextLine[1] + " , " + nextLine[2]);
				points.add(new SmithPoint(Double.parseDouble(nextLine[0]), Double.parseDouble(nextLine[1]),
						Color.web(nextLine[2])));
        	} else {
        		isFirst = false;
//        		System.out.println("label: " + nextLine[0] + " , " + nextLine[1] + " , " + nextLine[2]);
//        		points.add(new SmithPoint(0.0, 5.0, Color.web(DARK_GRAY_CHART)));
        	}
         }
		return points;
	}

	@SuppressWarnings({ "deprecation", "resource" })
	public static ArrayList<SmithPoint> readReactCSV() throws IOException {
		CSVReader reader = new CSVReader(new FileReader(CSV_FILE_REACT), ';');

        // read line by line
        String[] nextLine;
        ArrayList<SmithPoint> points = new ArrayList<SmithPoint>();

        boolean isFirst = true;
        while ((nextLine = reader.readNext()) != null) {
        	if (!isFirst) {
        		// nextLine[] is an array of values from the line
//        		System.out.println("values: " + nextLine[0] + " , " + nextLine[1] + " , " + nextLine[2]);
				points.add(new SmithPoint(Double.parseDouble(nextLine[0]), Double.parseDouble(nextLine[1]),
						Color.web(nextLine[2]), false));
        	} else {
        		isFirst = false;
//        		System.out.println("label: " + nextLine[0] + " , " + nextLine[1] + " , " + nextLine[2]);
        	}
         }
		return points;
	}

	public double getResistance() {
		return resistance;
	}
	
	public double getStrokeWidthRes() {
		return strokeWidthRes;
	}
	
	public Color getStrokeColorRes() {
		return strokeColorRes;
	}

	public double getReactance() {
		return reactance;
	}

	public double getStrokeWidthReact() {
		return strokeWidthReact;
	}

	public Color getStrokeColorReact() {
		return strokeColorReact;
	}

}
