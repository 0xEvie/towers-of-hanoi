package main.java.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import main.java.Main;

public class StartScreenController
{
	@FXML public Button playButton;
	
	
	public void playButtonClick(ActionEvent e)
	{
		Main.app.startGame();
	}
}
