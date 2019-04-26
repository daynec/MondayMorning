package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Level {
	
	private Random rnd = new Random();
	
	private int currTime = 60;
	
	private Stage stage;
	
	private Timeline timeLoop;
	private Timeline gameLoop;
	private Timeline painLoop;
	
	private Pane levelLayer;
	private Pane infoLayer;
	private Pane backgroundLayer;
	
	private ProgressBar healthBar;
	private Label timer;
	
	private Image playerImage;
	private Image ghostImage;
	private Image pillowImage;
	private Image backgroundImage;
	
	private Player player;
	private ArrayList<Enemy> enemies = new ArrayList<>();
	private ArrayList<SleepAid> sleepAids = new ArrayList<>();
	
	private Scene scene;
	
	boolean collision = false;
	
	public void start(Stage stage) {
		
		this.stage = stage;
		
		Group root = new Group();
		
		levelLayer = new Pane();
		infoLayer = new Pane();
		backgroundLayer = new Pane();
		
		fillInfoLayer();
		
		root.getChildren().add(levelLayer);
		root.getChildren().add(infoLayer);
		root.getChildren().add(backgroundLayer);
		
		scene = new Scene( root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
		
		stage.setTitle("Level One");
		stage.setScene(scene);
		stage.show();
		
		loadGame();
		
		painLoop = new Timeline(new KeyFrame(Duration.seconds(5), event ->  {player.setHealth(player.getHealth() - 10);}));
		timeLoop = new Timeline(new KeyFrame(Duration.seconds(1), event -> clockEvent()));
		gameLoop = new Timeline(new KeyFrame(Duration.millis(50), event -> gameUpdateEvent()));
		
		painLoop.setCycleCount(Animation.INDEFINITE);
		timeLoop.setCycleCount(Animation.INDEFINITE);
		gameLoop.setCycleCount(Animation.INDEFINITE);
		
		painLoop.play();
		gameLoop.play();
		timeLoop.play();
		
		}
		
	private void clockEvent() {
		
		//Stops if the player's health hits zero
		if (player.getHealth() == 0) {
			timeLoop.stop();
		}
		
		if (currTime > 0) {
			currTime--;
			timer.setText(String.valueOf(currTime));
		} else {
			timeLoop.stop();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/application/view/YouWin.fxml"));
				stage.setTitle("Monday Morning");
				stage.setScene(new Scene(root));
				stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
		
		if (player.getHealth() == 0) {
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/application/view/YouLose.fxml"));
				stage.setTitle("Monday Morning");
				stage.setScene(new Scene(root));
				stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
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
		
		playerImage = new Image("/application/assets/textures/bed.png"); //"/application/view/YouLose.fxml"
		ghostImage = new Image("/application/assets/textures/Ghost.png");
		pillowImage = new Image("/application/assets/textures/pillow.png");
		backgroundImage = new Image("/application/assets/textures/LevelOneBackground.png");
		
		ImageView backgroundImageView = new ImageView(backgroundImage);
		backgroundLayer.getChildren().add(backgroundImageView);
		backgroundLayer.toBack();
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
		
		if (rnd.nextInt(100) > 1 || sleepAids.size() > 5) {
			return;
		}
		
		Image image = pillowImage;
		
		double x = rnd.nextDouble() * Settings.SCENE_WIDTH - 20;
		double y = rnd.nextDouble() * Settings.SCENE_HEIGHT;
		
		SleepAid pillow = new SleepAid(levelLayer, image, x, y, Settings.PILLOW_HEALING);
		
		sleepAids.add(pillow);
		
	}
	
	private void createEnemies() {
		
		if (rnd.nextInt(100) > 5 || enemies.size() > 10) {
			return;
		}
		
		Image image = ghostImage;
		double x = ThreadLocalRandom.current().nextDouble(50 ,Settings.SCENE_WIDTH - 50);
		double y = ThreadLocalRandom.current().nextDouble(50, Settings.SCENE_HEIGHT - 50);
		
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