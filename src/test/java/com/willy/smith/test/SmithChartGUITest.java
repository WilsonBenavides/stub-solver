package com.willy.smith.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import com.willy.smith.main.StubSolverMain;

import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SmithChartGUITest extends ApplicationTest {

	@Before
    public void before() throws Exception {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(StubSolverMain.class);
    }

	@Override
	public void start(Stage stage) throws Exception {
		stage.show();
		stage.toFront();
	}

	@Test
	public void hasTextButton() {
		Button button = lookup("#button").query();
		assertThat("AnimateFX", equalTo(button.getText()));
	}

}
