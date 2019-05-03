package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Sound;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class LossController implements Initializable
{
	
	@FXML private ImageView loseImageView = new ImageView();
	
	//Start loss theme (Dark souls)
	private String musicFile = "src/application/assets/sounds/dsDeath.wav";
	private Media sound = new Media(new File(musicFile).toURI().toString());
	private MediaPlayer mediaPlayer = new MediaPlayer(sound);
	//mediaPlayer.setLooping() = false;
	//mediaPlayer.cycleCount = 0;
	//this.mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
	
	private Sound lossSound = new Sound( musicFile, sound, mediaPlayer );
	
	@FXML
	private void returnMenu(ActionEvent event)
	{
		this.lossSound.stopSound();
		String musicFile = "src/application/assets/sounds/UI04.wav";
		Media sound = new Media(new File(musicFile).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		
		Sound lossSound = new Sound( musicFile, sound, mediaPlayer );
		lossSound.playSound( 0 ); //0 = no loop, 1 = loop
		
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
	
	@FXML
	private void hoverButton()
	{
		String soundFile = "src/application/assets/sounds/UI01.wav"; //hover sound
		Media sound = new Media(new File(soundFile).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		
		Sound buttonSound = new Sound( soundFile, sound, mediaPlayer );
		buttonSound.playSound( 0 );
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		//this.mediaPlayer.setStopTime(Duration.seconds(8));
		this.lossSound.playSound( 0 );
		this.loseImageView.setImage(new Image("/application/assets/textures/lose.png"));
	}
}
