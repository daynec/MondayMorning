package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * This class is the baseline for any actor in the game
 * @author Torin Maguire - ztd721
 *
 */
public abstract class Entity {
	protected Image image;
	protected ImageView imageView;
	
	protected Pane layer;
	
	//Current coordinates
	protected double x, y;
	//Distance to move to
	protected double dx, dy;
	//Width and height
	protected double w, h;
	
	protected double health, damage;
	
	protected double speed;
		
	public Entity(Pane layer, Image image, double x, double y, double dx, double dy, double speed) {
		this.layer = layer;
		this.image = image;
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.speed = speed;
		
		this.imageView = new ImageView(image);
		this.imageView.relocate(x, y);
		
		this.w = image.getWidth();
		this.h = image.getHeight();
		
		addToLayer();
	}
	
	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(double y) {
		this.y = y;
	}

	public void addToLayer() {
		this.layer.getChildren().add(imageView);
	}
	
	public void removeFromLayer() {
		this.layer.getChildren().remove(this.imageView);
	}
	
	public void move() {
		x += dx;
		y += dy;
	}
	
	public void updateUI() {
		imageView.relocate(x, y);
	}
	
	public boolean collision(Entity colEntity) {
		return ( colEntity.x + colEntity.w >= x && colEntity.y + colEntity.h >= y && colEntity.x <= x + w && colEntity.y <= y + h);
	}
	
}
