package application.controller;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TitleController {
	
	@FXML
	private void quitGame() {
		Platform.exit();
	}
	
	@FXML
	private void startGame(ActionEvent event) {
		try {
			Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("/application/view/Game.fxml"));
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}	
}
