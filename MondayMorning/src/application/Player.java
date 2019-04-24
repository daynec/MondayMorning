package application;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

/**
 * This class represents the player object
 * @author Torin Maguire - ztd721
 *
 */
public class Player extends Entity {

	private double playerMinX, playerMinY, playerMaxX, playerMaxY;
	
	private Input input;
	
	private double health;
		
	public Player(Pane layer, Image image, double x, double y, double speed, double health, Input input) {
		
		super(layer, image, x, y, speed);
		
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
	
	public void getDamagedBy(Enemy enemy) {
		if (health > 0) {
			health -= enemy.getDamage();
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
