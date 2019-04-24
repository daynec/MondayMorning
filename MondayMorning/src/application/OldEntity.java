package application;

import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class OldEntity extends Canvas
{
	private Rectangle hitbox;
	private Vector2 position;
	private Image sprite;
	private ImageView icon;
	
	public enum Coord
	{
		X, Y
	}
	
	public OldEntity(Vector2 pos)
	{

		super(0.0f, 0.0f);
		hitbox = new Rectangle();
		icon = new ImageView();
		this.position = pos;
		
		
	}
	
	public int getCord(Coord c)
	{
		switch(c)
		{
			case X:
			{
				return position.getX();
			} 
			
			case Y: 
			{
				return position.getY();
			}
		}
		
		return 0;
	}
	
	//Delay initialization of other parts of the entity class since it can bring down the whole program if something goes south.
	public void init(String assetPath)
	{
		sprite = Utils.loadAsset(assetPath);
		hitbox.setWidth(sprite.getWidth());
		hitbox.setHeight(sprite.getHeight());
		icon.setImage(sprite);
		super.setHeight(sprite.getHeight());
		super.setWidth(sprite.getWidth());
		Utils.Log("Successfully initilialized the entity.");
	}
	
	
	public ImageView getImageView()
	{
		return icon;
	}
	
	public Image getImage()
	{
		return sprite;
	}
	
	public void move(int dx, int dy)
	{
		int x  = position.getX();
		int y = position.getY();
		position.setX(x + dx);
		position.setY(y + dy);
		
		//need to update the hitbox
		hitbox.setX(position.getX());
		hitbox.setY(position.getY());
		
		//should do the same for the image view until further notice
		icon.setX(position.getX());
		icon.setY(position.getY());
		
		
	}
	
	public void draw(GraphicsContext gc)
	{
		gc.drawImage(sprite, position.getX(), position.getY());
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
