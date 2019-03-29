package application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TitleController {
	
	@FXML private Button quitButton = new Button();
	@FXML private Button startButton = new Button();
	
	@FXML
	private void quitGame() {
		Stage stage = (Stage) quitButton.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	private void startGame() {
		System.out.println("Derp");
	}	
}
