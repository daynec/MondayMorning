package application.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class LossController
{
	@FXML 
	private Button menuButton = new Button();
	
	/*Image image = new Image( "bg.png" );
	private ImageView bgImage = new ImageView();

	bgImage.setImage(image);
	*/
	
	@FXML
	private void returnMenu(ActionEvent event)
	{
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		Parent root = null;
		try
		{
			root = FXMLLoader.load(getClass().getResource( "/application/view/Title.fxml" ));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		stage.setTitle("Monday Morning");
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	//@Override
	/*@FXML
	public void setLoseImage()
	{
		try
		{
			//set the background image
			Image image = new Image( "/application/bg.png" );
			bgImage.setImage( image );
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}*/
}
