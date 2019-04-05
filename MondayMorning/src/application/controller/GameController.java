package application.controller;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import application.Entity;
import application.Utils;
import application.Vector2;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;



public class GameController implements Initializable
{
	
	@FXML 
	public Pane gamePane;
	private Canvas canvas;
	final int movementMultiplier = 10; //Will change later for something better.
	private Entity e;
	private Vector2 inputVector;
	
	public Vector2 getInput()
	{
		return inputVector;
	}
	
	public Entity getEntity()
	{
		return e;
	}
	
	private void drawGame()
	{
		GraphicsContext gc = canvas.getGraphicsContext2D();
		e.draw(gc);
	}
	
	
	
	@FXML
	private void handleKeyPress(KeyEvent evnt)
	{
		KeyCode code = evnt.getCode();
		int x = code == KeyCode.A ? -1 : code == KeyCode.D ? 1 : 0;
		int y = code == KeyCode.S ? 1 : code == KeyCode.W ? -1 : 0;
		
		inputVector.setX(x * movementMultiplier);
		inputVector.setY(y * movementMultiplier);
		
		Utils.Log("Attempting to move the entity, x = " + inputVector.getX() + " y = " + inputVector.getY());
		
		e.move(inputVector);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		Vector2 startPoint = new Vector2(0,0);
		Entity player = new Entity(startPoint);
		player.init("dogebread");
	}
}
