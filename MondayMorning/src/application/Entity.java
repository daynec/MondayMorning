package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class is the baseline for any actor in the game
 * @author Torin Maguire - ztd721
 *
 */
public class Entity {
	Image image;
	ImageView imageView;
	
	//Current coords
	double x, y;
	//Distance to move to
	double dx, dy;
	//Width and height
	double w, h;
	
	public Entity(Image image,ImageView imageView, double x, double y, double dx, double dy) {
		this.image = image;
		this.imageView = imageView;
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		
		this.imageView.relocate(x, y);
		
		this.w = image.getWidth();
		this.h = image.getHeight();
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
