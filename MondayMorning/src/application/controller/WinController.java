package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class WinController implements Initializable {
	
	@FXML ImageView winImageView = new ImageView();
	
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		winImageView.setImage(new Image("/application/assets/textures/win.png"));
	}
}
