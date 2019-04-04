package application;

import java.io.*;
import javafx.scene.image.Image;

public class Utils {
	
	//simple logger utility so we dont litter the code with normal println's everywhere
	public static void Log(String msg)
	{
		System.out.println(msg);
	}
	
	//helper function for loading the images 
	public static Image loadAsset(String filename)
	{
		Image i = null;
		try
		{
			i = new Image(new FileInputStream(filename));
		}
		
		catch (IOException e)
		{
			Log("Failed to load graphical asset " + filename);
			e.printStackTrace();
			//Temporary. But if the asset can't load, might as well bail out from the whole program
			//since a blank rectangle makes for a boring game
			System.exit(1);
		}
		return i;
	}

}

//Can't make a game without making some vectors
class Vector2
{
	private int x,y;
	
	public Vector2(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY() 
	{
		return y;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
}
