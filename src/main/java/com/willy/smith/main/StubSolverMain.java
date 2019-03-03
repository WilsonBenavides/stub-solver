package com.willy.smith.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class StubSolverMain extends Application {

	private static final double WIDTH = 100.0;
	private static final double HEIGHT = WIDTH * 0.618;
	private static final double SCALE = 10.0;

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
		StackPane root = new StackPane();
		primaryStage.setScene(createScene(root, WIDTH * SCALE, HEIGHT * SCALE));
		primaryStage.setTitle("Stub Solver");
		primaryStage.show();
	}

	private Scene createScene(Pane root, double width, double height) {
		Scene scene = new Scene(root, width, height);
		Button btn = new Button("AnimateFX");
		btn.setId("button");
		root.getChildren().add(btn);
		return scene;
	}
}
