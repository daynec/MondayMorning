package application.view;

import application.Settings;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameView {	
	private Stage stage;
	private Scene scene;
	
	ProgressBar healthBar;
	Label timer;
	
	public void initialize(Stage stage, String title) {
		this.stage = stage;
				
		Pane root = new Pane();
		
		ImageView background = new ImageView(new Image("/application/assets/textures/LevelOneBackground.png"));
		
		timer = new Label();
		//TODO link the timer text to update as the clock ticks in game model
		timer.relocate(225, 15);
		
		healthBar = new ProgressBar();
		healthBar.setProgress(1);
		healthBar.setStyle("-fx-accent: green;");
		healthBar.setPrefWidth(Settings.SCENE_WIDTH / 4);
		healthBar.setPrefHeight(30);
		healthBar.relocate(15, 10);
		
		root.getChildren().add(background);
		root.getChildren().add(timer);
		root.getChildren().add(healthBar);
		
		scene = new Scene(root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
		stage.setTitle(title);
		stage.setScene(scene);
	}
	
	public void display() {
		stage.show();
	}

	public Stage getStage() {
		return stage;
	}
	
	public void updateHealth(double health) {
		healthBar.setProgress(health / Settings.PLAYER_HEALTH);
	}
	
	public void drawGhost() {
		
	}
}
