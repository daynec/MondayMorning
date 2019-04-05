package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class World
{
	private Entity e;
	private Stage s;
	
	public World(Stage stage) 
	{
		e = new Entity(new Vector2(0,0));
		s = stage;
	}
	
	public void init()
	{
		e.init("lonk.png");
		
		final Duration aFrame = Duration.millis(1000/60);
		final KeyFrame singleFrame = new KeyFrame(aFrame, new EventHandler()
		{

			@Override
			public void handle(Event arg0)
			{
				
			}
		} );
	
		Utils.Log("Successfully initalized the game world.");
	
	}

}
