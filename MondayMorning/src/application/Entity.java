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
	Image image;
	ImageView imageView;
	
	Pane layer;
	
	//Current coords
	double x, y;
	//Distance to move to
	double dx, dy;
	//Width and height
	double w, h;
	
	double health, damage;
		
	public Entity(Pane layer, Image image, double x, double y, double dx, double dy) {
		this.layer = layer;
		this.image = image;
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		
		this.imageView = new ImageView(image);
		this.imageView.relocate(x, y);
		
		this.w = image.getWidth();
		this.h = image.getHeight();
		
		addToLayer();
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
