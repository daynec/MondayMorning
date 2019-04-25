package application.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LossController {
	
@FXML private Button menuButton = new Button();
	
	@FXML
	private void returnMenu(ActionEvent event) {
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/application/view/Title.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		stage.setTitle("Monday Morning");
		stage.setScene(new Scene(root));
		stage.show();
	}
}
