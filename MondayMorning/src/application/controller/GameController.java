package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Input;
import application.Player;
import application.Utils;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



/**
 * For now, this class represents the first level. Ideally, I'd like to have the player spawn with a health bar and a timer at the top of the screen. When the game
 * begins, the timer will start counting down, enemies will start spawning and either moving around or chasing the player, sleep aids will spawn, and the player's 
 * health bar will decrease. Sleep aids will restore the player's health, and if they don't collect any then they will die. Taking damage from enemies will also
 * cause damage to the player. Stationary hazards could be implemented as well.
 * @author Torin Maguire - ztd721
 *
 */
public class GameController implements Initializable {
	
	@FXML private ImageView playerImageView;
	private Player player;
	private Scene gameScene;
	boolean collision = false;
	private Image playerSprite;
	private Input input;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		//Checking to see if the scene has loaded
		if (gameScene == null) {
			Utils.Log("Scene did not load");
		}
		
		//Loads the player's sprite
		playerSprite = Utils.loadAsset("src/dogebread.jpg");
		
		//Creates player object on stage
		this.player = new Player(playerSprite, playerImageView, 50, 50, 0, 0, input, 0);
		
		//This is what gets the game to process time
		new AnimationTimer() {
			@Override
			public void handle(long arg0)
			{
				//Checks to see if the player is pressing a key
				player.processInput();
				//Moves the player character is a key is currently being pressed
				player.move();
				//Checks for collisions between the player and other objects
				checkCollisions();
				//Updates the player's sprite to match movement
				player.updateUI();
			}
		};
		
	}
	
	//Will be used to check collisions between the player, sleep aids, and enemies
	private void checkCollisions() {
		collision = false;
		//Should be set to check collisions between enemy objects but is currently null until enemies are made
		if (player.collision(null)) {
			collision = true;
		}
	}
	
	//Loads the player object with control listeners
	public void setUp(Input input) {
		input.addListeners();
	}
	
}
