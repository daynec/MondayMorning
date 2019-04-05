package application;

import java.io.*;
import javafx.scene.image.Image;

public class Utils {
	
	//simple logger utility so we dont litter the code with normal println's everywhere
	public static void Log(String msg)
	{
		System.out.println("[+] " + msg);
	}
	
	public static void CriticalFailure(String finalWords, Exception e)
	{
		System.out.println("[-] " + finalWords);
		if(e != null)
		{
			e.printStackTrace();
		}
		System.exit(1);
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
			//Temporary. But if the asset can't load, might as well bail out from the whole program
			//since a blank rectangle makes for a boring game
			CriticalFailure("Failed to load graphical asset " + filename, e);
		}
		return i;
	}

}

