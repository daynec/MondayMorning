package application;

import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Entity extends Canvas
{
	private Rectangle hitbox;
	private Vector2 pos;
	private Image sprite;
	private ImageView view;
	
	public enum Coord
	{
		X, Y
	}
	
	public Entity(Vector2 pos)
	{

		super(0.0f, 0.0f);
		hitbox = new Rectangle();
		view = new ImageView();
		this.pos = pos;
		
	}
	
	public int getCord(Coord c)
	{
		switch(c)
		{
			case X:
			{
				return pos.getX();
			} 
			
			case Y: 
			{
				return pos.getY();
			}
		}
		
		return 0;
	}
	
	//Delay initilization of other parts of the entity class since it can bring down the whole program if something goes south.
	public void init(String assetPath)
	{
		sprite = Utils.loadAsset(assetPath);
		hitbox.setWidth(sprite.getWidth());
		hitbox.setHeight(sprite.getHeight());
		view.setImage(sprite);
		super.setHeight(sprite.getHeight());
		super.setWidth(sprite.getWidth());
		Utils.Log("Successfully initilialized the entity.");
	}
	
	
	public ImageView getImageView()
	{
		return view;
	}
	
	public Image getImage()
	{
		return sprite;
	}
	
	public void move(Vector2 direction)
	{
		//TODO(brandon): check to see if we even need these temp vars
		//also check if we can actually get away with the int -> double type cast and not lose anything meaningful
		int x  = pos.getX();
		int y = pos.getY();
		pos.setX(x + direction.getX());
		pos.setY(y + direction.getY());
		
		//need to update the hitbox
		hitbox.setX(pos.getX());
		hitbox.setY(pos.getY());
		
		//should do the same for the image view until further notice
		view.setX(pos.getX());
		view.setY(pos.getY());
		
		
	}
	
	public void draw(GraphicsContext gc)
	{
		gc.drawImage(sprite, pos.getX(), pos.getY());
	}
	
	//Need this for collision detection
	public boolean checkCollision(Rectangle other)
	{
		if(hitbox.getBoundsInParent().intersects(other.getBoundsInParent()))
		{
			return true;
		}
		return false;
	}
}
