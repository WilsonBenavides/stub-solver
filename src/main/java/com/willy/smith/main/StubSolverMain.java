package com.willy.smith.main;

import static com.willy.smith.util.UtilConstant.HEIGHT;
import static com.willy.smith.util.UtilConstant.SCALE;
import static com.willy.smith.util.UtilConstant.WIDTH;
import static com.willy.smith.util.UtilConstant.TITLE;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class StubSolverMain extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
		StackPane root = new StackPane();
		primaryStage.setScene(createScene(root, WIDTH * SCALE, HEIGHT * SCALE));
		primaryStage.setTitle(TITLE);
		primaryStage.show();
	}

	private Scene createScene(Pane root, double width, double height) {
		Scene scene = new Scene(root, width, height);
		Button btn = new Button("AnimateFX");
		btn.setId("button");
		btn.setOnAction(actionEvent -> btn.setText("click"));
		root.getChildren().add(btn);
		return scene;
	}
}
