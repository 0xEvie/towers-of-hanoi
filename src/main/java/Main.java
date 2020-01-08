package main.java;
	
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import main.java.controllers.FXController;
import main.java.models.GameState;
import main.java.models.Move;
import main.java.models.PoleChild;
import main.java.utils.SaveIO;


public class Main extends Application {
	public int numberOfBlocks;
	public Button blocks[];
	VBox root;
	Stage primary;
	ArrayList<PoleChild> pole1children;
	ArrayList<PoleChild> pole2children;
	ArrayList<PoleChild> pole3children;
	DataFormat bFormat = new DataFormat("");
	
	public VBox dragSource;
	public VBox dragTarget;

	public static Main app;
	
	@Override
	public void start(Stage primaryStage) {
		app=this;
		
		try {
			primary = primaryStage;
			
			GridPane startScreen = FXMLLoader.load(getClass().getResource("../resources/views/startscreen.fxml"));
			Scene scene1 = new Scene(startScreen,760, 500);
			scene1.getStylesheets().add(getClass().getResource("../resources/styles/application.css").toExternalForm());
			primary.setScene(scene1);
			primary.show();
			
		} catch(Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle(e.getMessage());
			alert.setHeaderText("Launch Error");
			alert.setContentText(e.getMessage());
			alert.show();
		}
	}
	
	public void exitToStartScreen()
	{
		try
		{
			GridPane startScreen = FXMLLoader.load(getClass().getResource("../resources/views/startscreen.fxml"));
			Scene scene3 = new Scene(startScreen,760, 500);
			scene3.getStylesheets().add(getClass().getResource("../resources/styles/application.css").toExternalForm());
			primary.setScene(scene3);
		} catch (Exception e)
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle(e.getMessage());
			alert.setHeaderText(null);
			alert.setContentText("");
			alert.show();
		}
	}
	
	public void winScreen()
	{
		try
		{
			GridPane winScreen = FXMLLoader.load(getClass().getResource("../resources/views/winscreen.fxml"));
			Scene scene4 = new Scene(winScreen,760, 500);
			scene4.getStylesheets().add(getClass().getResource("../resources/styles/application.css").toExternalForm());
			primary.setScene(scene4);
		} catch (Exception e)
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle(e.getMessage());
			alert.setHeaderText(null);
			alert.setContentText("");
			alert.show();
		}
	}
	public void startGame()
	{
		try
		{
			root = FXMLLoader.load(getClass().getResource("../resources/views/towersofhanoi.fxml"));
			Scene scene2 = new Scene(root,760,500);
			scene2.getStylesheets().add(getClass().getResource("../resources/styles/application.css").toExternalForm());
			primary.setScene(scene2);
			
			VBox pole1 = (VBox) root.lookup("#pole1");
			VBox pole2 = (VBox) root.lookup("#pole2");
			VBox pole3 = (VBox) root.lookup("#pole3");
			ChoiceBox cb = (ChoiceBox) root.lookup("#choicebox");
			
			pole1.setOnDragOver( e -> {
				if (e.getDragboard().hasContent(bFormat)) 
				{
					
				}
		          e.acceptTransferModes(TransferMode.MOVE);
				});
			
			pole2.setOnDragOver( e -> {
				if (e.getDragboard().hasContent(bFormat)) 
				{
					
				}
		          e.acceptTransferModes(TransferMode.MOVE);
				});
			
			pole3.setOnDragOver( e -> {
				if (e.getDragboard().hasContent(bFormat)) 
				{
					
				}
		          e.acceptTransferModes(TransferMode.MOVE);
				});
			
			pole1.setOnDragDropped(e -> {poleDragDrop(e);});
			pole2.setOnDragDropped(e -> {poleDragDrop(e);});
			pole3.setOnDragDropped(e -> {poleDragDrop(e);});
			
			init(root, pole1, cb);
		} catch (Exception e)
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle(e.getMessage());
			alert.setHeaderText("Error initializing game");
			alert.setContentText("");
			alert.show();
		}
	}
	
	@Override
	public void stop()
	{
		try
		{
		    FXController f = FXController.controller;
		   
			ArrayList<Move> moveList = f.getMoveList();
			int moveCount = f.getMoveCount();
			
			ArrayList<PoleChild> pole1children = new ArrayList<PoleChild>();
			ArrayList<PoleChild> pole2children = new ArrayList<PoleChild>();
			ArrayList<PoleChild> pole3children = new ArrayList<PoleChild>();
			
			VBox pole1 = (VBox) root.lookup("#pole1");
			VBox pole2 = (VBox) root.lookup("#pole2");
			VBox pole3 = (VBox) root.lookup("#pole3");
			
			if (!pole1.getChildren().isEmpty())
			{
				for (int i = 0; i < pole1.getChildren().size(); i++)
				{
					PoleChild p = new PoleChild(pole1.getChildren().get(i).getId(), i, ((Region) pole1.getChildren().get(i)).getWidth());
					pole1children.add(p);
				}
			}
			
			if (!pole2.getChildren().isEmpty())
			{
				for (int i = 0; i < pole2.getChildren().size(); i++)
				{
					PoleChild p = new PoleChild(pole2.getChildren().get(i).getId(), i, ((Region) pole2.getChildren().get(i)).getWidth());
					pole2children.add(p);
				}
			}
			
			if (!pole1.getChildren().isEmpty())
			{
				for (int i = 0; i < pole3.getChildren().size(); i++)
				{
					PoleChild p = new PoleChild(pole3.getChildren().get(i).getId(), i, ((Region) pole3.getChildren().get(i)).getWidth());
					pole3children.add(p);
				}	
			}
			
			GameState g = new GameState(pole1children, pole2children, pole3children, moveCount, moveList);
			
			SaveIO s = new SaveIO();
			
			try
			{
				s.save(g);
			} catch (IOException e)
			{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle(e.getMessage());
				alert.setHeaderText("Error saving game");
				alert.setContentText("");
				alert.show();
			}
		}catch(Exception e)
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle(e.getMessage());
			alert.setHeaderText(null);
			alert.setContentText("");
			alert.show();
		}
	}
	
	public void buttonDrag(MouseEvent e)
	{
		Button b = (Button) e.getSource();
		dragSource = (VBox) b.getParent();
   	     try
   	     {
	   		 Dragboard db = b.startDragAndDrop(TransferMode.MOVE);
	   		 db.setDragView(b.snapshot(null, null)); //button ghost
	   		 ClipboardContent cc = new ClipboardContent();
	   		 cc.put(bFormat, " "); //created data format above
	   		 db.setContent(cc); //add content to clipboard	
   	     }catch(Exception ex)
   	     {
   	    	
   	     }
	}
	
	public void poleDragDrop(DragEvent e)
	{
		dragTarget = (VBox) e.getSource();
		FXController.controller.moveBrick(dragSource, dragTarget);
		
		e.setDropCompleted(true);
	}
	
	public void init(VBox root, VBox pole1, ChoiceBox cb)
	{
		GameState g = null;
		SaveIO s = new SaveIO();
		
		VBox pole2 = (VBox) root.lookup("#pole2");
		VBox pole3 = (VBox) root.lookup("#pole3");
		Label countLabel = (Label) root.lookup("#countLabel");
		
		ArrayList<PoleChild> pole1children = new ArrayList<PoleChild>();
		ArrayList<PoleChild> pole2children = new ArrayList<PoleChild>();
		ArrayList<PoleChild> pole3children = new ArrayList<PoleChild>();
		
		try
		{
			if (FXController.controller.gameWon)
			{
				FXController.controller.gameWon=false;
				throw new Exception();
			}
			
			g = s.read();
				if (!g.getPole1Children().isEmpty())
				{
					for (int i = 0; i < g.getPole1Children().size(); i++)
					{
						Button b = new Button();
						b.setPrefWidth(g.getPole1Children().get(i).getWidth());
						b.setId(g.getPole1Children().get(i).getButtonId());;
						pole1.getChildren().add(g.getPole1Children().get(0).getIndex(), b);
						b.setOnDragDetected(e -> {buttonDrag(e);});
						b.toFront();
					}	
				}
				
				if (!g.getPole2Children().isEmpty())
				{
					for (int i = 0; i < g.getPole2Children().size(); i++)
					{
						Button b = new Button();
						b.setPrefWidth(g.getPole2Children().get(i).getWidth());
						b.setId(g.getPole2Children().get(i).getButtonId());;
						pole2.getChildren().add(g.getPole2Children().get(0).getIndex(), b);
						b.setOnDragDetected(e -> {buttonDrag(e);});
						b.toFront();
					}	
				}
				
				if (!g.getPole3Children().isEmpty())
				{
					for (int i = 0; i < g.getPole3Children().size(); i++)
					{
						Button b = new Button();
						b.setPrefWidth(g.getPole3Children().get(i).getWidth());
						b.setId(g.getPole3Children().get(i).getButtonId());;
						pole3.getChildren().add(g.getPole3Children().get(0).getIndex(), b);
						b.setOnDragDetected(e -> {buttonDrag(e);});
						b.toFront();
					}	
				}
				
				FXController.controller.moveList = g.getMoveList();
				FXController.controller.moveCount = g.getMoveCount();
				countLabel.setText("Move count: "+FXController.controller.moveCount);

				numberOfBlocks = (pole1.getChildren().size() + pole2.getChildren().size() + pole3.getChildren().size());
				if ((pole1.getChildren().size() + pole2.getChildren().size() + pole3.getChildren().size()) < 3)
					throw new Exception();
				
			} catch (Exception e)
			{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Reading File");
				alert.setHeaderText(null);
				alert.setContentText("");
				alert.show();
				
				startFromScratch(cb,pole1, pole2, pole3, countLabel);
			}
		
	}
	
	public void startFromScratch(ChoiceBox cb, VBox pole1, VBox pole2, VBox pole3, Label countLabel)
	{
		//Erase everything
		pole1.getChildren().clear();
		pole2.getChildren().clear();
		pole3.getChildren().clear();
		FXController.controller.target = null;
		FXController.controller.source = null;
		
		FXController.controller.moveList.clear();
		
		FXController.controller.moveCount = 0;
		countLabel.setText("Move count: "+FXController.controller.moveCount);
		
		numberOfBlocks = Integer.parseInt((String)cb.getValue());
				
		if (Integer.parseInt((String)cb.getValue()) < 3)
		{
			numberOfBlocks = 3;
		}
		
		int[] sizes = {25, 50, 75, 100, 125, 150, 175, 200};

		for(int i = 0; i <= numberOfBlocks-1; i++)
		{
			Button b = new Button();
			b.setId("b"+(i+1)+"");
			pole1.getChildren().add(b);
			b.setMaxWidth(sizes[i]);
			b.setMinWidth(sizes[i]);
			b.setPrefWidth(sizes[i]);
			b.setOnDragDetected(e -> {buttonDrag(e);});
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
