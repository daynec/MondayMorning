package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class represents the player object
 * @author Torin Maguire - ztd721
 *
 */
public class Player extends Entity {

	double playerMinX, playerMinY, playerMaxX, playerMaxY;
	
	Input input;
	
	double speed;
	
	public Player(Image image, ImageView imageView, double x, double y, double dx, double dy, Input input, double speed) {
		super(image, imageView, x, y, dx, dy);
		
		this.input = input;
		this.speed = speed;
		
		init();
	}
	
	private void init() {
		playerMinX = 0 - image.getWidth();
		playerMaxX = Settings.SCENE_WIDTH - image.getWidth();
		playerMinY = 0 - image.getHeight();
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
