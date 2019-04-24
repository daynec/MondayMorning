package application;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Enemy extends Entity {
	
	double damage;
	
	public Enemy(Pane layer, Image image, double x, double y, double dx, double dy, double damage) {
		super(layer, image, x, y, dx, dy);
		
		this.damage = damage;
	}
	
	public double getDamage() {
		return damage;
	}
	
}
