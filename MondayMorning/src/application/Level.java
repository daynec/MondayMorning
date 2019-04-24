package application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Level {
	
	private Random rnd = new Random();
	
	private int currTime = 60;
	
	private Pane levelLayer;
	private Pane infoLayer;
	
	private ProgressBar healthBar;
	private Label timer;
	
	private Image playerImage;
	private Image ghostImage;
	
	private Player player;
	private ArrayList<Enemy> hazards = new ArrayList<>();
	
	private Scene scene;
	
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
		
		Timeline timeLoop = new Timeline(new KeyFrame(Duration.seconds(1), event -> clockwork()));
		timeLoop.setCycleCount(Animation.INDEFINITE);
		
		Timeline gameLoop = new Timeline(new KeyFrame(Duration.millis(50), event -> gameUpdate()));
		gameLoop.setCycleCount(Animation.INDEFINITE);
		
		gameLoop.play();
		timeLoop.play();
		
		}
	
	private void clockwork() {
		if (currTime > 0) {
			currTime--;
			timer.setText(String.valueOf(currTime));
		}
	}
	
	private void gameUpdate() {
		player.processInput();
		
		createEnemies();
		
		player.move();
		hazards.forEach(Entity -> Entity.move());
		
		checkCollisions();
		removeEnemies(hazards);
		
		hazards.forEach(Entity -> Entity.updateUI());
		player.updateUI();
		updateInfo();
	}
	
	private void fillInfoLayer() {
		timer = new Label();
		timer.setText(String.valueOf(currTime));
		
		infoLayer.getChildren().add(timer);
		
		timer.relocate(225, 15);
		
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
		
	private void removeEnemies(ArrayList<Enemy> hazards) {
		
		Iterator<Enemy> removeChecklist = hazards.iterator();
		while (removeChecklist.hasNext()) {
			Enemy enemy = removeChecklist.next();
			
			if (enemy.isRemovable()) {
				enemy.removeFromLayer();
				removeChecklist.remove();
			}
		}
	}
	
	private void createEnemies() {
		if (rnd.nextInt(100) > 5) {
			return;
		}
		
		Image image = ghostImage;
				
		double x = rnd.nextDouble() * Settings.SCENE_WIDTH / 2.0;
		double y = rnd.nextDouble() * Settings.SCENE_HEIGHT / 2.0;
		
		Enemy ghost = new Enemy(levelLayer, image, x, y, 0, 0, 10);
		
		hazards.add(ghost);
	}
	
	private void checkCollisions() {
		collision = false;
		
		for (Enemy hazard: hazards) {
			if (player.collision(hazard)) {
				collision = true;
				player.getDamagedBy(hazard);
				hazard.setRemovable(true);
			}
		}
	}
	
}