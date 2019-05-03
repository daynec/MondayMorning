package application.model;

import application.Sound;

import java.io.File;
import java.util.Random;

import application.Settings;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * This class represents the player object
 * @author Torin Maguire - ztd721, Dayne Closser - ehh448
 *
 */
public class PlayerModel extends Entity {

	private double playerMinX, playerMinY, playerMaxX, playerMaxY;
	
	private Input input;
	
	private double health;
	
	//Player damage sound 01
	private String soundFile = "src/application/assets/sounds/Damage01.wav";
	private Media dmgSound1 = new Media(new File(soundFile).toURI().toString());
	private MediaPlayer mediaPlayer = new MediaPlayer(dmgSound1);
	private Sound playerSound = new Sound( soundFile, dmgSound1, mediaPlayer );
	//End player damage sound 01
	
	//Player damage sound 02
	private String soundFile2 = "src/application/assets/sounds/Damage02.wav";
	private Media dmgSound2 = new Media(new File(soundFile2).toURI().toString());
	private MediaPlayer mediaPlayer2 = new MediaPlayer(dmgSound2);
	private Sound playerSound2 = new Sound( soundFile2, dmgSound2, mediaPlayer2 );
	//End player damage sound 02
		
	//Player damage sound 03
	private String soundFile3 = "src/application/assets/sounds/Damage02.wav";
	private Media dmgSound3 = new Media(new File(soundFile3).toURI().toString());
	private MediaPlayer mediaPlayer3 = new MediaPlayer(dmgSound3);
	private Sound playerSound3 = new Sound( soundFile3, dmgSound3, mediaPlayer3 );
	//End player damage sound 03
	
	//Player damage sound 04
	private String soundFile4 = "src/application/assets/sounds/Damage02.wav";
	private Media dmgSound4 = new Media(new File(soundFile4).toURI().toString());
	private MediaPlayer mediaPlayer4 = new MediaPlayer(dmgSound4);
	private Sound playerSound4 = new Sound( soundFile4, dmgSound4, mediaPlayer4 );
	//End player damage sound 04
		
	public PlayerModel(Pane layer, Image image, double x, double y, double speed, double health, Input input) {
		
		super(layer, image, x, y, speed);
		
		//this.playerSound = playerSound;
		this.input = input;
		this.health = health;
		
		init();
		
	}
	
	/**
	 * @return the playerMinX
	 */
	public double getPlayerMinX() {
		return playerMinX;
	}

	/**
	 * @param playerMinX the playerMinX to set
	 */
	public void setPlayerMinX(double playerMinX) {
		this.playerMinX = playerMinX;
	}

	/**
	 * @return the playerMinY
	 */
	public double getPlayerMinY() {
		return playerMinY;
	}

	/**
	 * @param playerMinY the playerMinY to set
	 */
	public void setPlayerMinY(double playerMinY) {
		this.playerMinY = playerMinY;
	}

	/**
	 * @return the playerMaxX
	 */
	public double getPlayerMaxX() {
		return playerMaxX;
	}

	/**
	 * @param playerMaxX the playerMaxX to set
	 */
	public void setPlayerMaxX(double playerMaxX) {
		this.playerMaxX = playerMaxX;
	}

	/**
	 * @return the playerMaxY
	 */
	public double getPlayerMaxY() {
		return playerMaxY;
	}

	/**
	 * @param playerMaxY the playerMaxY to set
	 */
	public void setPlayerMaxY(double playerMaxY) {
		this.playerMaxY = playerMaxY;
	}

	/**
	 * @return the input
	 */
	public Input getInput() {
		return input;
	}

	/**
	 * @param input the input to set
	 */
	public void setInput(Input input) {
		this.input = input;
	}

	/**
	 * @return the health
	 */
	public double getHealth() {
		return health;
	}

	/**
	 * @param health the health to set
	 */
	public void setHealth(double health) {
		this.health = health;
	}

	private void init() {
		playerMinX = 0;
		playerMaxX = Settings.SCENE_WIDTH - image.getWidth();
		playerMinY = 0;
		playerMaxY = Settings.SCENE_HEIGHT - image.getHeight();
	}
	
	public void processInput() {
		if (input.isUp()) {
			dy = -speed;
		} else if (input.isDown()) {
			dy = speed;
		} else {
			dy = 0d;
		}
		
		if (input.isLeft()) {
			dx = -speed;
		} else if (input.isRight()) {
			dx = speed;
		} else {
			dx = 0d;
		}
	}
	
	@Override
	public void move() {
		super.move();
		checkBounds();
	}
	
	public boolean isAlive() {
		return Double.compare(health, 0) > 0;
	}
	
	public void getDamagedBy(GhostModel ghostModel)
	{
		if (health > 0)
		{
			health -= ghostModel.getDamage();			
		}
	}
	
	public void getHealedBy(PillowModel pillowModel)
	{
		if (health < Settings.PLAYER_HEALTH)
		{
			health += pillowModel.getHealing();
			
			//Play heal sound
		}
	}
	
	private void checkBounds() {
		if (Double.compare(y, playerMinY) < 0) {
			y = playerMinY;
		} else if (Double.compare(y, playerMaxY) > 0) {
			y = playerMaxY;
		}
		
		if (Double.compare(x, playerMinX) < 0) {
			x = playerMinX;
		} else if (Double.compare(x, playerMaxX) > 0) {
			x = playerMaxX;
		}
	}

}
