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

	double playerMinX, playerMinY, playerMaxX, playerMaxY;
	
	Input input;
	
	double health;
	
	double speed;
	
	public Player(Pane layer, Image image, double x, double y, double dx, double dy, double health, Input input, double speed) {
		super(layer, image, x, y, dx, dy);
		
		this.input = input;
		this.speed = speed;
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
