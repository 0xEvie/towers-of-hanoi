package main.java.models;

import java.io.Serializable;
import java.util.ArrayList;

public class GameState implements Serializable
{
	ArrayList<PoleChild> pole1children, pole2children, pole3children;
	private ArrayList<Move> moveList;
	private int moveCount;
	
	
	public GameState(ArrayList<PoleChild> pole1children, ArrayList<PoleChild> pole2children, ArrayList<PoleChild> pole3children, int moveCount, ArrayList<Move> moveList)
	{
		this.pole1children = pole1children;
		this.pole2children = pole2children;
		this.pole3children = pole3children;
		this.moveCount = moveCount;
		this.moveList = moveList;
	}
	
	public ArrayList<PoleChild> getPole1Children()
	{
		return pole1children;
	}
	
	public ArrayList<PoleChild> getPole2Children()
	{
		return pole2children;
	}
	
	public ArrayList<PoleChild> getPole3Children()
	{
		return pole3children;
	}
	
	public int getMoveCount()
	{
		return moveCount;
	}
	
	
	public ArrayList<Move> getMoveList()
	{
		return moveList;
	}
}
