package application.model;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

/**
 * This class is the baseline for any actor in the game
 * @author Torin Maguire - ztd721
 *
 */
public abstract class Entity extends GameObject {
	
	protected double dx, dy;
	
	protected double speed;
		
	public Entity(Pane layer, Image image, double x, double y, double speed) {
		
		super(layer, image, x, y);
		
		this.speed = speed;
		this.dx = 0;
		this.dy = 0;
		
	}

	/**
	 * @return the dx
	 */
	public double getDx() {
		return dx;
	}

	/**
	 * @param dx the dx to set
	 */
	public void setDx(double dx) {
		this.dx = dx;
	}

	/**
	 * @return the dy
	 */
	public double getDy() {
		return dy;
	}

	/**
	 * @param dy the dy to set
	 */
	public void setDy(double dy) {
		this.dy = dy;
	}
	
	/**
	 * @return the speed
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void move() {
		x += dx;
		y += dy;
	}
	
}
