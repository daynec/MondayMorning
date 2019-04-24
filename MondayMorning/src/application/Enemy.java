package application;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Enemy extends Entity {
	
	private double damage;
	
	private boolean removable = false;
	
	public Enemy(Pane layer, Image image, double x, double y, double speed, double damage) {
		super(layer, image, x, y, speed);
		
		this.damage = damage;
		
	}
	
	/**
	 * @return the damage
	 */
	public double getDamage() {
		return damage;
	}

	/**
	 * @param damage the damage to set
	 */
	public void setDamage(double damage) {
		this.damage = damage;
	}

	/**
	 * @return the removable
	 */
	public boolean isRemovable() {
		return removable;
	}

	/**
	 * @param removable the removable to set
	 */
	public void setRemovable(boolean removable) {
		this.removable = removable;
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
