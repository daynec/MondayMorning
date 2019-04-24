package application;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Enemy extends Entity {
	
	private double damage;
	
	private boolean removable = false;
	
	public Enemy(Pane layer, Image image, double x, double y, double dx, double dy, double speed, double damage) {
		super(layer, image, x, y, dx, dy, speed);
		
		this.damage = damage;
	}
	
	public double getDamage() {
		return damage;
	}
	
	public void setRemovable(boolean removable) {
		this.removable = removable;
	}
	
	public boolean isRemovable() {
		return removable;
	}
	
	public void pathToPlayer(Player player) {
		dx = player.getX() - x;
		dy = player.getY() - y;
		double norm = Math.sqrt(dx * dx + dy * dy);
		if (norm != 0) {
			dx *= (speed / norm);
			dy *= (speed / norm);
		}
	}
	
}
