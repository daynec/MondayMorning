package application;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Level {
		
	Pane levelLayer;
	
	Image playerImage;
	
	Player player;
	
	Scene scene;
	
	boolean collision = false;
	
	public void start(Stage stage) {
		
		Group root = new Group();
		
		levelLayer = new Pane();
		
		root.getChildren().add(levelLayer);
		
		scene = new Scene( root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
		
		stage.setScene(scene);
		stage.show();
		
		loadGame();
				
		AnimationTimer gameLoop = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				
				player.processInput();
				
				player.move();
				
				checkCollisions();
				
				player.updateUI();
				
			}
		};
		
		gameLoop.start();
	}
	
	private void loadGame() {
		playerImage = new Image("dogebread.jpg");
		Input input = new Input(scene);
		
		input.addListeners();
		
		Image image = playerImage;
		
		double x = (Settings.SCENE_WIDTH - image.getWidth()) / 2.0;
		double y = Settings.SCENE_HEIGHT * 0.7;
		
		player = new Player(levelLayer, image, x, y, 0, 0, Settings.PLAYER_HEALTH, input, Settings.PLAYER_SPEED);
	}
	
	private void checkCollisions() {
		collision = false;
	}
	
}