package application;

import javafx.animation.PauseTransition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Enemy extends Entity {
	
	private double damage;
		
	public Enemy(Pane layer, Image image, double x, double y, double speed, double damage) {
		
		super(layer, image, x, y, speed);
		
		this.damage = damage;	
		
		PauseTransition fox = new PauseTransition(Duration.seconds(5));
		fox.setOnFinished(die -> {
			this.setRemovable(true);
		});
		fox.play();
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
