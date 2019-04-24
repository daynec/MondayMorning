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
	
	private Timeline timeLoop;
	private Timeline gameLoop;
	
	private Pane levelLayer;
	private Pane infoLayer;
	
	private ProgressBar healthBar;
	private Label timer;
	
	private Image playerImage;
	private Image ghostImage;
	private Image pillowImage;
	
	private Player player;
	private ArrayList<Enemy> enemies = new ArrayList<>();
	private ArrayList<SleepAid> sleepAids = new ArrayList<>();
	
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
		
		timeLoop = new Timeline(new KeyFrame(Duration.seconds(1), event -> clockEvent()));
		gameLoop = new Timeline(new KeyFrame(Duration.millis(50), event -> gameUpdateEvent()));
		
		timeLoop.setCycleCount(Animation.INDEFINITE);
		gameLoop.setCycleCount(Animation.INDEFINITE);
		
		gameLoop.play();
		timeLoop.play();
		
		}
	
	private void clockEvent() {
		
		if (currTime > 0) {
			currTime--;
			timer.setText(String.valueOf(currTime));
		} else {
			timeLoop.stop();
		}
		
	}
	
	private void gameUpdateEvent() {
		
		player.processInput();
		
		createEnemies();
		createSleepAids();
		
		player.move();
		enemies.forEach(Enemy -> Enemy.pathToPlayer(player));
		enemies.forEach(Enemy -> Enemy.move());
		
		checkCollisions();
		removeEnemies(enemies);
		removeSleepAids(sleepAids);
		
		enemies.forEach(Entity -> Entity.updateUI());
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
		pillowImage = new Image("pillow.png");
		
		Input input = new Input(scene);
		
		input.addListeners();
		
		Image image = playerImage;
		
		double x = (Settings.SCENE_WIDTH - image.getWidth()) / 2.0;
		double y = Settings.SCENE_HEIGHT * 0.7;
		
		player = new Player(levelLayer, image, x, y, Settings.PLAYER_SPEED, Settings.PLAYER_HEALTH, input);
		
	}
		
	private void removeEnemies(ArrayList<Enemy> enemies) {
		
		Iterator<Enemy> garbageList = enemies.iterator();
		
		while (garbageList.hasNext()) {
		
			Enemy garbage = garbageList.next();
			
			if (garbage.isRemovable()) {
				
				garbage.removeFromLayer();
				garbageList.remove();
				
			}
			
		}
		
	}
	
private void removeSleepAids(ArrayList<SleepAid> sleepAids) {
		
		Iterator<SleepAid> garbageList = sleepAids.iterator();
		
		while (garbageList.hasNext()) {
		
			SleepAid garbage = garbageList.next();
			
			if (garbage.isRemovable()) {
				
				garbage.removeFromLayer();
				garbageList.remove();
				
			}
			
		}
		
	}
	
	private void createSleepAids() {
		
		if (rnd.nextInt(100) > 15) {
			return;
		}
		
		Image image = pillowImage;
		
		double x = rnd.nextDouble() * Settings.SCENE_WIDTH;
		double y = rnd.nextDouble() * Settings.SCENE_HEIGHT;
		
		SleepAid pillow = new SleepAid(levelLayer, image, x, y, Settings.PILLOW_HEALING);
		
		sleepAids.add(pillow);
		
	}
	
	private void createEnemies() {
		
		if (rnd.nextInt(100) > 5) {
			return;
		}
		
		Image image = ghostImage;
				
		double x = rnd.nextDouble() * Settings.SCENE_WIDTH;
		double y = rnd.nextDouble() * Settings.SCENE_HEIGHT;
		
		Enemy ghost = new Enemy(levelLayer, image, x, y, Settings.GHOST_SPEED, Settings.GHOST_DAMAGE);
		
		enemies.add(ghost);
		
	}
	
	private void checkCollisions() {
		
		collision = false;
		
		for (SleepAid sleepAid : sleepAids) {
			
			if (player.collision(sleepAid)) {
				
				collision = true;
				
				player.getHealedBy(sleepAid);
				
				sleepAid.setRemovable(true);
				
			}
			
		}
		
		for (Enemy enemy: enemies) {
			
			if (player.collision(enemy)) {
				
				collision = true;
				
				player.getDamagedBy(enemy);
				
				enemy.setRemovable(true);
				
			}
			
		}
		
	}
	
}