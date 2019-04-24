package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;



/**
 * For now, this class represents the first level. Ideally, I'd like to have the player spawn with a health bar and a timer at the top of the screen. When the game
 * begins, the timer will start counting down, enemies will start spawning and either moving around or chasing the player, sleep aids will spawn, and the player's 
 * health bar will decrease. Sleep aids will restore the player's health, and if they don't collect any then they will die. Taking damage from enemies will also
 * cause damage to the player. Stationary hazards could be implemented as well.
 * @author Torin Maguire - ztd721
 *
 */
public class GameController implements Initializable {
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
}
