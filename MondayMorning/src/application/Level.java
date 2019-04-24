package application;

import java.util.HashMap;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.animation.Timeline;;

public class Level
{
	
	private Scene gameScene;
	private Group sceneNodes;
	private static Timeline gameLoop;
	private int fps;
	private OldEntity player;
	final int MOVEMENT_MULTIPLIER = 10;
	private HashMap<KeyCode, Integer> pressedKeys;
	
	public Level(double width, double height)
	{
		player = new OldEntity(new Vector2(0,0));
		sceneNodes = new Group();
		gameScene = new Scene(sceneNodes, width, height, Color.WHITE);
		pressedKeys = new HashMap<KeyCode, Integer>();
		gameScene.setOnKeyPressed(key -> {pressedKeys.put(key.getCode(), MOVEMENT_MULTIPLIER);});
		gameScene.setOnKeyReleased(key -> {pressedKeys.remove(key.getCode());});
	}
	
	public void init()
	{
		player.init("src/dogebread.jpg");
		Utils.Log("Successfully initalized the game world.");
	}
	
	public void update(double deltaTime) {
		
	}
	
	public void draw(GraphicsContext gc) {
		player.draw(gc);
	}
	
	public Scene getScene() { return s; }
	
	public Group getGroup() { return g; }

}
