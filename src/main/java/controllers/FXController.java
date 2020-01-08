package main.java.controllers;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.java.Main;
import main.java.models.Move;

public class FXController
{
	public static FXController controller;
	@FXML public VBox pole1;
	@FXML public VBox pole2;
	@FXML public VBox pole3;
	@FXML public Button t1b, t2b, t3b, undo;
	@FXML public Label countLabel;
	@FXML public ChoiceBox choicebox;
	@FXML public VBox root;
	@FXML public Rectangle rect1, rect2, rect3;
	
	public boolean restarted = false;
	
	public static ArrayList<Move> moveList = new ArrayList<Move>();
	
	public boolean gameWon = false;
	
	public VBox source = null;
	public VBox target = null;
	public int moveCount = 0;
	
	@FXML
	public void initialize() 
	{
	    controller = this;
		
	}
	public VBox getPole1()
	{
		return pole1;
	}
	
	public VBox getPole2()
	{
		return pole2;
	}
	
	public VBox getPole3()
	{
		return pole3;
	}
	
	public ArrayList<Move> getMoveList()
	{
		return moveList;
	}
	
	public int getMoveCount()
	{
		return moveCount;
	}
	
	public boolean winStatus()
	{
		return gameWon;
	}
	
	public void towerClick(MouseEvent e)
	{
		VBox selection = null;
		
		if (!gameWon)
		{
			if (source!=null & target!=null)
			{
				source = null;
				target = null;
			}
			
			selection = (VBox) e.getSource();
			
			if (source==null)
			{
				source = selection;
				if (selection==pole1)
				{
					rect1.setFill(Color.LIMEGREEN);
				}
				else if (selection==pole2)
				{
					rect2.setFill(Color.LIMEGREEN);
				}
				else if (selection==pole3)
				{
					rect3.setFill(Color.LIMEGREEN);
				}
				
			}
			
			else if (source!=null && target==null) //gets here once, doesn't run this
			{
				target = selection;
				moveBrick(source, target);
				
				rect1.setFill(Color.BLACK);
				rect2.setFill(Color.BLACK);
				rect3.setFill(Color.BLACK);
			}
		}
	}
	public void buttonClick(ActionEvent e)
	{
		VBox selection = null;
		if (!gameWon)
		{
			if (source!=null & target!=null)
			{
				source = null;
				target = null;
			}
			
			if (e.getSource()==t1b)
			{
				selection = pole1;
			}
			else if (e.getSource() == t2b)
			{
				selection = pole2;
			}
			else
			{
				selection = pole3;
			}
			
			if (source==null)
			{
				source = selection;
				if (source==pole1)
				{
					rect1.setFill(Color.LIMEGREEN);
				}
				else if (source==pole2)
				{
					rect2.setFill(Color.LIMEGREEN);
				}
				else if (source==pole3)
				{
					rect3.setFill(Color.LIMEGREEN);
				}
				
			}
			
			else if (source!=null && target==null) //gets here once, doesn't run this
			{
				target = selection;
				moveBrick(source, target);
				
				rect1.setFill(Color.BLACK);
				rect2.setFill(Color.BLACK);
				rect3.setFill(Color.BLACK);
			}
		}
	}
	
	public void dragBrick(ActionEvent e)
	{
		
	}
	
	
	public void moveBrick(VBox source, VBox target)
	{ 
		Button targetb = null;
		double targetbwidth = 200;
		
		if (!gameWon)
		{
			if (!source.getChildren().isEmpty()) //check empty before seeing if get(0) exists
			{
				if (!target.getChildren().contains(source.getChildren().get(0)) & target.getId() != source.getId())
				{
					Button sourceb = (Button) source.getChildren().get(0);
					
					if (!target.getChildren().isEmpty())
					{
						targetb = (Button) target.getChildren().get(0);
						targetbwidth = targetb.getWidth();
					}
					
					if (sourceb.getWidth() < targetbwidth)
					{
						target.getChildren().add(0, source.getChildren().get(0));
						moveCount++;
						countLabel.setText("Move count: "+moveCount);
						
						Move m = new Move(target.getId(), source.getId(), sourceb.getId());
						moveList.add(m);
						
						if (target.getId().equals("pole2") && target.getChildren().size()== Main.app.numberOfBlocks )
						{
							win();
							Main.app.winScreen();
							
						}
					}
				}
			}
		}
	}
	
	public void win()
	{
		gameWon = true;
		
	}
	public void restart()
	{
		Main.app.startFromScratch(choicebox, pole1, pole2, pole3, countLabel);
	}
	
	public void undoMove()
	{
		if (!gameWon)
		{
			if (!moveList.isEmpty())
			{
				Move lastMove = moveList.get(moveList.size()-1);
				
				VBox sourcepole = (VBox) root.lookup(lastMove.getSourceId());

				sourcepole.getChildren().add(0, root.lookup(lastMove.getSourceButtonId()));
				moveCount--;
				countLabel.setText("Move count: "+moveCount);
				moveList.remove(moveList.size()-1);
			}
		}
	}
}
