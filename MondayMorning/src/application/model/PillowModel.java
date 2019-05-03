package application.model;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class PillowModel extends GameObject {
	
	private double healing;

	public PillowModel(Pane layer, Image image, double x, double y, double healing) {
		
		super(layer, image, x, y);
		
		this.healing = healing;
		
	}

	/**
	 * @return the healing
	 */
	public double getHealing() {
		return healing;
	}

	/**
	 * @param healing the healing to set
	 */
	public void setHealing(double healing) {
		this.healing = healing;
	}

}
