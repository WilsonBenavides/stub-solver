package com.willy.smith.util;

import static com.willy.smith.util.UtilConstant.DARK_GRAY_CHART;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVReader;

import javafx.scene.paint.Color;

public class SmithPoint {

	private static final String CSV_FILE_PATH = "src/main/resources/plot_values_excel.csv";
	
	private Double resistance;
	private Double strokeWidth;
	private Color strokeColor;
	
	public SmithPoint() {}

	public SmithPoint(Double resistance, Double strokeWidth, Color strokeColor) {
		this.resistance = resistance;
		this.strokeWidth = strokeWidth;
		this.strokeColor = strokeColor;
	}
	
	@SuppressWarnings({ "deprecation", "resource" })
	public static ArrayList<SmithPoint> readCSV() throws IOException {
		CSVReader reader = new CSVReader(new FileReader(CSV_FILE_PATH), ';');

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
        		points.add(new SmithPoint(0.0, 5.0, Color.web(DARK_GRAY_CHART)));
        	}
         }
		return points;
	}

	public Double getResistance() {
		return resistance;
	}
	
	public Double getStrokeWidth() {
		return strokeWidth;
	}
	
	public Color getStrokeColor() {
		return strokeColor;
	}

}
