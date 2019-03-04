package com.willy.smith.test;

import static org.testfx.api.FxToolkit.registerPrimaryStage;
import static org.testfx.api.FxToolkit.setupApplication;

import org.junit.Before;
import org.testfx.framework.junit.ApplicationTest;

import com.willy.smith.main.StubSolverMain;

import javafx.stage.Stage;

public class SmithChartGUITest extends ApplicationTest {

	@Before
    public void before() throws Exception {
        registerPrimaryStage();
        setupApplication(StubSolverMain.class);
    }

	@Override
	public void start(Stage stage) throws Exception {
		stage.show();
		stage.toFront();
	}

//	@Test
//	public void hasTextButton() {
//		verifyThat("#button", hasText("AnimateFX"));
//	}
	
//	@Test
//	public void hasClickButton() {
//		clickOn("#button");
//		verifyThat("#button", hasText("click"));
//	}
	
}
