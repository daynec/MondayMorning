package application.controller;

import application.GameModel;
import application.view.GameView;

public class GameController {
	
	GameModel model;
	GameView view;
	
	public GameController(GameModel model, GameView view) {
		this.model = model;
		this.view = view;
	}
	
	public void initialize() {
		
	}
}
