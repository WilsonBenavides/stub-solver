package com.willy.smith.main;

import static com.willy.smith.util.UtilConstant.TITLE;
import static com.willy.smith.util.UtilConstant.SCREEN_SCALE;

import com.willy.smith.chart.SmithChart;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class StubSolverMain extends Application {

	private SmithChart smith;

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

		this.smith = new SmithChart(screenBounds);

		StackPane root = new StackPane();
		double wdt = screenBounds.getWidth() * SCREEN_SCALE;
		double hgt = screenBounds.getHeight() * SCREEN_SCALE;
		primaryStage.setScene(createScene(root, wdt, hgt));
		primaryStage.setTitle(TITLE);
		primaryStage.show();
	}

	private Scene createScene(Pane root, double width, double height) {
		Scene scene = new Scene(root, width, height);

		root.getChildren().addAll(this.smith);
		return scene;
	}
	
}
