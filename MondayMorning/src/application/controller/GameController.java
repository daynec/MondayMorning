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



public class GameController implements Initializable {
	
	@FXML private ImageView playerImageView;
	private Player player;
	private Scene gameScene;
	boolean collision = false;
	private Input input;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		if (gameScene == null) {
			Utils.Log("Scene did not load");
		}
		
		new AnimationTimer() {
			@Override
			public void handle(long arg0)
			{
				player.processInput();
				player.move();
				checkCollisions();
				player.updateUI();
			}
		};
		
	}
		
	private void checkCollisions() {
		collision = false;
		
		if (player.collision(null)) {
			collision = true;
		}
	}
	
	public void setUp(Input input) {
		double x = 50;
		double y = 50;
		Image playerSprite = Utils.loadAsset("src/dogebread.jpg");
		this.input = input;
		input.addListeners();
		this.player = new Player(playerSprite, playerImageView, x, y, 0, 0, input, 0);
	}
	
}
