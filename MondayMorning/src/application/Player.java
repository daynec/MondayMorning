package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
		
	public Player(Pane layer, Image image, double x, double y, double dx, double dy, double speed, double health, Input input) {
		super(layer, image, x, y, dx, dy, speed);
		
		this.input = input;
		this.health = health;
		
		init();
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
	
	public double getHealth() {
		return health;
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
