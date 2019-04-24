package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class GameObject {
	
	//The pane the object is currently on
	protected Pane layer;
	//Object sprite
	protected Image image;
	//Object's ImageView within the scene
	protected ImageView imageView;
	//X and Y coordinates
	protected double x, y;
	//Width and height
	protected double w, h;
	
	public GameObject(Pane layer, Image image, double x, double y) {
		
		this.layer = layer;
		this.image = image;
		this.x = x;
		this.y = y;
		this.imageView = new ImageView(image);
		this.w = image.getWidth();
		this.h = image.getHeight();
		
		this.imageView.relocate(x, y);
		
		addToLayer();
		
	}

	/**
	 * @return the layer
	 */
	public Pane getLayer() {
		return layer;
	}

	/**
	 * @param layer the layer to set
	 */
	public void setLayer(Pane layer) {
		this.layer = layer;
	}

	/**
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * @return the imageView
	 */
	public ImageView getImageView() {
		return imageView;
	}

	/**
	 * @param imageView the imageView to set
	 */
	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
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

	/**
	 * @return the w
	 */
	public double getW() {
		return w;
	}

	/**
	 * @param w the w to set
	 */
	public void setW(double w) {
		this.w = w;
	}

	/**
	 * @return the h
	 */
	public double getH() {
		return h;
	}

	/**
	 * @param h the h to set
	 */
	public void setH(double h) {
		this.h = h;
	}
	
	public double getCenterX() {
		return x + w / 2;
	}
	
	public double getCenterY() {
		return y + h / 2;
	}
	
	public void addToLayer() {
		this.layer.getChildren().add(imageView);
	}
	
	public void removeFromLayer() {
		this.layer.getChildren().remove(imageView);
	}
	
	public void updateUI() {
		imageView.relocate(x, y);
	}
	
	public boolean collision(GameObject otherObj) {
		return ( otherObj.x + otherObj.w >= x && otherObj.y + otherObj.h >= y && otherObj.x <= x + w && otherObj.y <= y + h);
	}
	
}
