package application.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import application.Level;
import application.Sound;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class TitleController implements Initializable
{
	//Start title theme
	private String musicFile = "src/application/assets/sounds/UnderTheHotSun.mp3";
	private Media sound = new Media(new File(musicFile).toURI().toString());
	private MediaPlayer mediaPlayer = new MediaPlayer(sound);
	//mediaPlayer.setLooping(true); //Loops if you'd like nvm not operational
			
	private Sound titleSound = new Sound( musicFile, sound, mediaPlayer );
	
	@FXML
	private void startGame(ActionEvent event)
	{	
		this.titleSound.stopSound();
		String musicFile = "src/application/assets/sounds/UI04.wav";
		Media sound = new Media(new File(musicFile).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		
		Sound titleSound = new Sound( musicFile, sound, mediaPlayer );
		titleSound.playSound( 0 ); //good god don't loop this sound
		
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		
		Level levelOne = new Level();
		
		levelOne.start(stage);
	}
	
	@FXML
	private void quitGame()
	{
		String musicFile = "src/application/assets/sounds/UI01.wav";
		Media sound = new Media(new File(musicFile).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		
		Sound titleSound = new Sound( musicFile, sound, mediaPlayer );
		titleSound.playSound( 0 );
		
		Platform.exit();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		titleSound.playSound( 1 );
	}
}
