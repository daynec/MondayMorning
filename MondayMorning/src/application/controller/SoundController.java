package application.controller;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 * 
 * @author Dayne Closser | ehh448
 * Adds sounds and music to the game. Work in progress.
 */
public class SoundController
{
	@FXML
	private void playTitleMusic( ActionEvent event )
	{
		//@FXML private Button menuButton = new Button();
		
		Media pick = new Media(new File("/application/assets/sounds/Requiem.mp3").toURI().toString());
		MediaPlayer player = new MediaPlayer(pick);
		MediaView mediaView = new MediaView(player);
		
		player.setAutoPlay(true);
		//titleMediaView FXID of the mediaView on Title.fxml
		
		// Add mediaView to the scene
		// After the stage is shown:
		
		//application.controller.SoundController.player.play();
	}
}