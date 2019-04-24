package application.controller;

import application.Level;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class TitleController {
	
	@FXML
	private void startGame(ActionEvent event) {
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		Level levelOne = new Level();
		levelOne.start(stage);
	}
	
	@FXML
	private void quitGame() {
		Platform.exit();
	}
	
}
