package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
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

public class GameModel {
	
	private Random rnd = new Random();
	
	private int currTime = Settings.LEVEL_ONE_TIME;
	
	private final IntegerProperty time = new SimpleIntegerProperty();
	
	private Timeline timeLoop;
	private Timeline gameLoop;
	private Timeline painLoop;
	
	private Image playerImage;
	private Image ghostImage;
	private Image pillowImage;
	private Image backgroundImage;
	
	private PlayerModel playerModel;
	private ArrayList<GhostModel> ghostModels = new ArrayList<>();
	private ArrayList<PillowModel> pillowModels = new ArrayList<>();
	
	boolean collision = false;
	
	public IntegerProperty timeProperty() {
		return time;
	}
	
	public final int getTime() {
		return timeProperty().get();
	}
	
	public final void setTime(int time) {
		timeProperty().set(time);
	}
	
	public void start(Stage stage) {
		
		
		loadGame();
		
		painLoop = new Timeline(new KeyFrame(Duration.seconds(5), event ->  {playerModel.setHealth(playerModel.getHealth() - 10);}));
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
		
		//Shuts down game if player wins or loses
		if (playerModel.getHealth() == 0 || currTime == 0) {
			timeLoop.stop();
			gameLoop.stop();
			painLoop.stop();
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
				e.printStackTrace();
			}
			
		}
		
	}
	
	private void gameUpdateEvent() {
		
		playerModel.processInput();
		
		createEnemies();
		createHealers();
		
		playerModel.move();
		ghostModels.forEach(Enemy -> Enemy.pathToPlayer(playerModel));
		ghostModels.forEach(Enemy -> Enemy.move());
		
		checkCollisions();
		removeEnemies(ghostModels);
		removeHealers(pillowModels);
		
		ghostModels.forEach(Entity -> Entity.updateUI());
		playerModel.updateUI();
		updateInfo();
		
		if (playerModel.getHealth() == 0) {
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
	
	private void loadGame() {
		
		playerImage = new Image("/application/assets/textures/bed.png"); //"/application/view/YouLose.fxml"
		ghostImage = new Image("/application/assets/textures/Ghost.png");
		pillowImage = new Image("/application/assets/textures/pillow.png");
		
		ImageView backgroundImageView = new ImageView(backgroundImage);
		backgroundLayer.getChildren().add(backgroundImageView);
		backgroundLayer.toBack();
		Input input = new Input(scene);
		
		input.addListeners();
		
		Image image = playerImage;
		
		double x = (Settings.SCENE_WIDTH - image.getWidth()) / 2.0;
		double y = Settings.SCENE_HEIGHT * 0.7;
		
		playerModel = new PlayerModel(levelLayer, image, x, y, Settings.PLAYER_SPEED, Settings.PLAYER_HEALTH, input);
		
	}
		
	private void removeEnemies(ArrayList<GhostModel> ghostModels) {
		
		Iterator<GhostModel> garbageList = ghostModels.iterator();
		
		while (garbageList.hasNext()) {
		
			GhostModel garbage = garbageList.next();
			
			if (garbage.isRemovable()) {
				
				garbage.removeFromLayer();
				garbageList.remove();
				
			}
			
		}
		
	}
	
private void removeHealers(ArrayList<PillowModel> pillowModels) {
		
		Iterator<PillowModel> garbageList = pillowModels.iterator();
		
		while (garbageList.hasNext()) {
		
			PillowModel garbage = garbageList.next();
			
			if (garbage.isRemovable()) {
				
				garbage.removeFromLayer();
				garbageList.remove();
				
			}
			
		}
		
	}
	
	private void createHealers() {
		
		if (rnd.nextInt(100) > 1 || pillowModels.size() > 5) {
			return;
		}
		
		Image image = pillowImage;
		
		double x = rnd.nextDouble() * Settings.SCENE_WIDTH - 20;
		double y = rnd.nextDouble() * Settings.SCENE_HEIGHT;
		
		PillowModel pillowModel = new PillowModel(levelLayer, image, x, y, Settings.PILLOW_HEALING);
		
		pillowModels.add(pillowModel);
		
	}
	
	private void createEnemies() {
		
		if (rnd.nextInt(100) > 5 || ghostModels.size() > 10) {
			return;
		}
		
		Image image = ghostImage;
		double x = ThreadLocalRandom.current().nextDouble(50 ,Settings.SCENE_WIDTH - 50);
		double y = ThreadLocalRandom.current().nextDouble(50, Settings.SCENE_HEIGHT - 50);
		
		GhostModel ghostModel = new GhostModel(levelLayer, image, x, y, Settings.GHOST_SPEED, Settings.GHOST_DAMAGE);
				
		ghostModels.add(ghostModel);
		
	}
	
	private void checkCollisions() {
		
		collision = false;
		
		for (PillowModel pillowModel : pillowModels) {
			
			if (playerModel.collision(pillowModel)) {
				
				collision = true;
				
				playerModel.getHealedBy(pillowModel);
				
				pillowModel.setRemovable(true);
				
			}
			
		}
		
		for (GhostModel ghostModel: ghostModels) {
			
			if (playerModel.collision(ghostModel)) {
				
				collision = true;
				
				playerModel.getDamagedBy(ghostModel);
				
				ghostModel.setRemovable(true);
				
			}
			
		}
		
	}
	
}