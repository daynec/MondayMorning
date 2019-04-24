package application;

import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Level {
		
	Pane levelLayer;
	Pane infoLayer;
	
	ProgressBar healthBar;
	
	Image playerImage;
	Image ghostImage;
	
	Player player;
	ArrayList<Enemy> hazards = new ArrayList<>();
	
	Scene scene;
	
	boolean collision = false;
	
	public void start(Stage stage) {
		
		Group root = new Group();
		
		levelLayer = new Pane();
		infoLayer = new Pane();
		
		fillInfoLayer();
		
		root.getChildren().add(levelLayer);
		root.getChildren().add(infoLayer);
		
		scene = new Scene( root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
		
		stage.setTitle("Level One");
		stage.setScene(scene);
		stage.show();
		
		loadGame();
		
		Timeline gameLoop = new Timeline(new KeyFrame(Duration.millis(50), event -> {

			player.processInput();
			
			createEnemies();
			
			player.move();
			hazards.forEach(Entity -> Entity.move());
			
			checkCollisions();
			
			hazards.forEach(Entity -> Entity.updateUI());
			player.updateUI();
			updateInfo();
			
		}));
		gameLoop.setCycleCount(Animation.INDEFINITE);
		gameLoop.play();
		}
	
	private void fillInfoLayer() {
		healthBar = new ProgressBar();
		healthBar.setProgress(1);
		healthBar.setStyle("-fx-accent: green;");
		healthBar.setPrefWidth(Settings.SCENE_WIDTH / 4);
		healthBar.setPrefHeight(30);
		
		
		infoLayer.getChildren().add(healthBar);
		
		double x = 15;
		double y = 10;
		
		healthBar.relocate(x, y);
	}
	
	private void updateInfo() {
		healthBar.setProgress(player.getHealth() / Settings.PLAYER_HEALTH);
	}
	
	private void loadGame() {
		playerImage = new Image("bed.png");
		ghostImage = new Image("Ghost.png");
		
		Input input = new Input(scene);
		
		input.addListeners();
		
		Image image = playerImage;
		
		double x = (Settings.SCENE_WIDTH - image.getWidth()) / 2.0;
		double y = Settings.SCENE_HEIGHT * 0.7;
		
		player = new Player(levelLayer, image, x, y, 0, 0, Settings.PLAYER_HEALTH, input, Settings.PLAYER_SPEED);
	}
	
	private void createEnemies() {
		Image image = ghostImage;
				
		double x = Settings.SCENE_WIDTH / 2.0;
		double y = Settings.SCENE_HEIGHT / 2.0;
		
		Enemy ghost = new Enemy(levelLayer, image, x, y, 0, 0, 1);
		
		hazards.add(ghost);
	}
	
	private void checkCollisions() {
		collision = false;
		
		for (Enemy hazard: hazards) {
			if (player.collision(hazard)) {
				collision = true;
				player.getDamagedBy(hazard);
			}
		}
	}
	
}