package main.java.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import main.java.Main;

public class WinScreenController
{
	@FXML public Button playAgainButton;
	@FXML public Button exitButton;

	
	public void exitButtonClick(ActionEvent e)
	{
		Main.app.exitToStartScreen();
	}
	
	public void playAgainButton(ActionEvent e)
	{
		Main.app.startGame();
	}
}
