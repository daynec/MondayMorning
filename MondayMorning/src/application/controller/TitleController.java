package application.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import application.Sound;
import application.model.LevelModel;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TitleController implements Initializable
{
	@FXML private ImageView titleImageView = new ImageView();
	
	//Start title theme
	private String musicFile; 			// = "src/application/assets/sounds/UnderTheHotSun.mp3";
	private Media sound; 				// = new Media(new File(musicFile).toURI().toString());
	private MediaPlayer mediaPlayer; 	// = new MediaPlayer(sound);
	private Sound titleSound; 			// = new Sound( musicFile, sound, mediaPlayer );
	
	@FXML
	private void startGame(ActionEvent event)
	{	
		this.titleSound.stopSound();
		
		String musicFileSG = "src/application/assets/sounds/UI04.wav"; //click sound
		Media soundSG = new Media(new File(musicFileSG).toURI().toString());
		MediaPlayer mediaPlayerSG = new MediaPlayer(soundSG);
		Sound titleSoundSG = new Sound( musicFileSG, soundSG, mediaPlayerSG );
		titleSoundSG.playSound( 0 ); //0 don't loop 1 loops
		
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		
		LevelModel levelOne = new LevelModel();
		
		levelOne.start(stage);
	}
	
	@FXML
	private void quitGame()
	{
		String musicFileQG = "src/application/assets/sounds/UI04.wav";
		Media soundQG = new Media(new File(musicFileQG).toURI().toString());
		MediaPlayer mediaPlayerQG = new MediaPlayer(soundQG);
		Sound titleSoundQG = new Sound( musicFileQG, soundQG, mediaPlayerQG );
		titleSoundQG.playSound( 0 );
		
		PauseTransition delay = new PauseTransition(Duration.seconds(1));
		delay.setOnFinished(event -> Platform.exit());
		delay.play();
	}
	
	@FXML
	private void hoverButton()
	{
		String soundFileHover = "src/application/assets/sounds/UI01.wav"; //hover sound
		Media soundHover = new Media(new File(soundFileHover).toURI().toString());
		MediaPlayer mediaPlayerHover = new MediaPlayer(soundHover);
		Sound buttonSound = new Sound( soundFileHover, soundHover, mediaPlayerHover );
		buttonSound.playSound( 0 );
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		this.musicFile = "src/application/assets/sounds/UnderTheHotSun.mp3";
		this.sound = new Media(new File(musicFile).toURI().toString());
		this.mediaPlayer = new MediaPlayer(sound);
		this.titleSound = new Sound( musicFile, sound, mediaPlayer );
		this.titleSound.playSound( 1 );
		
		this.titleImageView.setImage(new Image("/application/assets/textures/titlebgb.png"));
	}
}
