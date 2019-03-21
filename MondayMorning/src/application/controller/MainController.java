package application.controller;

import java.io.File;
import java.io.IOException;

import application.Main;
import application.model.Library;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class MainController implements EventHandler<ActionEvent> {

	@Override
	public void handle( ActionEvent event )
	{
		//fromage et une pomme de terre
		//une poisson c'est tres bon!
		/*
		// Have user use DirectoryChooser to select books
		DirectoryChooser d = new DirectoryChooser();
		File dir = d.showDialog( new Stage() );
		
		// Have Library read in all books - titles and authors only!
		try
		{
			Library.loadBooks( dir );
		}
		catch( IOException e )
		{
			// TODO: There was a problem! Alert the user via the GUI.
			e.printStackTrace();
		}
		
		// Redirect user to next view - Library
		try
		{
			Parent root = FXMLLoader.load(getClass().getResource("../view/Library.fxml"));
			Main.stage.setScene(new Scene(root, 600, 600));
			Main.stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		*/
	}
}