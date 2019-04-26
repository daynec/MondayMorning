package application;

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
public class Sound
{
	private String musicFile; 				//= "src/application/assets/sounds/UnderTheHotSun.mp3";
	private Media sound; 					//= new Media(new File(musicFile).toURI().toString());
	private static MediaPlayer mediaPlayer; //= new MediaPlayer(sound);
	
	public Sound( String musicFile, Media sound, MediaPlayer mediaPlayer )
	{
		this.musicFile = musicFile;
		this.sound = sound;
		this.mediaPlayer = mediaPlayer;
	}
	
	public void playSound( int loop )
	{
		this.mediaPlayer.play();
		//this.mediaPlayer.setLooping(true);
		if( loop == 1 )
		{
			this.mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		}
		else
		{
			//your ears are saved
		}
	}
	
	public void stopSound()
	{
		this.mediaPlayer.stop();
	}
}