package application;

import java.util.BitSet;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * This class contains everything needed to record the player's inputs and tell the player class which way to move
 * @author Torin Maguire - ztd721
 *
 */
public class Input {
	
	private BitSet keysBitSet = new BitSet();
	
	private KeyCode upKey = KeyCode.UP;
	private KeyCode downKey = KeyCode.DOWN;
    private KeyCode leftKey = KeyCode.LEFT;
    private KeyCode rightKey = KeyCode.RIGHT;
    
    private Scene scene;
    
    public Input(Scene scene) {
    	this.scene = scene;
    }
    
    public Scene getScene() {
    	return scene;
    }
    
    public void addListeners() {
    	scene.addEventFilter(KeyEvent.KEY_PRESSED, keyPressedHandler);
    	scene.addEventFilter(KeyEvent.KEY_RELEASED, keyReleasedHandler);
    }
    
    public void removeListeners() {
    	scene.addEventFilter(KeyEvent.KEY_PRESSED, keyPressedHandler);
    	scene.addEventFilter(KeyEvent.KEY_RELEASED, keyReleasedHandler);
    }
    
    private EventHandler<KeyEvent> keyPressedHandler = new EventHandler<KeyEvent>() {
    	@Override
    	public void handle(KeyEvent event) {
    		keysBitSet.set(event.getCode().ordinal(), true);
    	}
    };
    
    private EventHandler<KeyEvent> keyReleasedHandler = new EventHandler<KeyEvent>() {
    	@Override
    	public void handle(KeyEvent event) {
    		keysBitSet.set(event.getCode().ordinal(), false);
    	}
    };
    
    public boolean isUp() {
    	System.out.println("Testing");
        return keysBitSet.get( upKey.ordinal()) && !keysBitSet.get( downKey.ordinal());
    }

    public boolean isDown() {
        return keysBitSet.get( downKey.ordinal()) && !keysBitSet.get( upKey.ordinal());
    }

    public boolean isLeft() {
        return keysBitSet.get( leftKey.ordinal()) && !keysBitSet.get( rightKey.ordinal());  
    }

    public boolean isRight() {
        return keysBitSet.get( rightKey.ordinal()) && !keysBitSet.get( leftKey.ordinal());
    }
}
