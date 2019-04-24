package application.controller;

import java.io.IOException;

import application.Input;
import application.Utils;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TitleController {
	
	@FXML
	private void startGame(ActionEvent event) {
		
		try {
		//Load stage and fxml
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/Game.fxml"));
		Parent root = loader.load();
		//Retrieving the controller
		GameController gControl = (GameController) loader.getController();
		Scene gameScene = new Scene(root);
		//Connecting the scene to the input listeners because the scene isn't available during GameController initialization
		Input input = new Input(gameScene);
		gControl.setUp(input);
		stage.setScene(gameScene);
		stage.show();
		}
		
		catch (IOException e)
		{
			Utils.CriticalFailure("Cannot initialize game scene.", e);
		}
		
	}	
	
	@FXML
	private void quitGame() {
		Platform.exit();
	}
	
}
